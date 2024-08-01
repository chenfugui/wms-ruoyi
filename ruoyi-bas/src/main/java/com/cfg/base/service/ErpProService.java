package com.cfg.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.domain.*;
import com.cfg.base.mapper.*;
import com.cfg.base.pojo.dto.ErpProDTO;
import com.cfg.base.pojo.query.ErpProQuery;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.ConvertUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 服装产品管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProService {
    @Autowired
    private ErpProMapper erpProMapper;

    @Autowired
    private ErpProProcessMapper proProcessMapper;

    @Autowired
    private ErpProSizeMapper proSizeMapper;

    @Autowired
    private ErpProColorMapper proColorMapper;

    @Autowired
    private ErpProPriceMapper proPriceMapper;

    private PropertyResolver propertyResolver;
    @Autowired
    private IdGenService idGenService;
    @Autowired
    private ErpProSizeService proSizeService;
    @Autowired
    private ErpProColorService proColorService;
    @Autowired
    private ErpProPriceService proPriceService;
    @Autowired
    private ErpProProcessService proProcessService;

    /**
     * 查询服装产品管理
     *
     * @param proId 服装产品管理主键
     * @return 服装产品管理
     */
    public ErpProDTO selectByProId(Long proId) {
        ErpPro product =  erpProMapper.selectById(proId);
        ErpProDTO erpProDTO= ConvertUtils.convert(product, ErpProDTO.class);
        if (null != product) {
            ErpProProcess proProcess = proProcessMapper.selectById(proId);
            proProcess.setProId(proId);
            List<ErpProProcess> proProcesseList = proProcessMapper.selectByEntity(proProcess);
            erpProDTO.setProcList(proProcesseList);
            ErpProColor proColor = new ErpProColor();
            proColor.setProId(proId);
            List<ErpProColor> proColorList = proColorMapper.selectByEntity(proColor);
            erpProDTO.setColorList(proColorList);
            ErpProSize proSize = new ErpProSize();
            proSize.setProId(proId);
            List<ErpProSize> proSizeList = proSizeMapper.selectByEntity(proSize);
            erpProDTO.setSizeList(proSizeList);
            ErpProPrice proPrice = new ErpProPrice();
            proPrice.setProId(proId);
            List<ErpProPrice> proPriceList = proPriceMapper.selectByEntity(proPrice);
            erpProDTO.setPriceList(proPriceList);
        }
        return erpProDTO;
    }

    /**
     * 查询服装产品管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装产品管理
     */
    public List<ErpPro> selectList(ErpProQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpPro> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String proCode = query.getProCode();
        if (!StringUtils.isEmpty(proCode)) {
            qw.eq("pro_code", proCode);
        }
        String proNameLike = query.getProNameLike();
        if (!StringUtils.isEmpty(proNameLike)) {
            qw.like("pro_name", proNameLike);
        }
        String proDesc = query.getProDesc();
        if (!StringUtils.isEmpty(proDesc)) {
            qw.eq("pro_desc", proDesc);
        }
        String proType = query.getProType();
        if (!StringUtils.isEmpty(proType)) {
            qw.eq("pro_type", proType);
        }
        String proMemo = query.getProMemo();
        if (!StringUtils.isEmpty(proMemo)) {
            qw.eq("pro_memo", proMemo);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Long empid = query.getEmpid();
        if (empid != null) {
            qw.eq("emp_id", empid);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        return erpProMapper.selectList(qw);
    }

    /**
     * 新增服装产品管理
     *
     * @param erpPro 服装产品管理
     * @return 结果
     */
    public int insert(ErpPro erpPro) {
        erpPro.setDelFlag(0);
        erpPro.setCreateTime(LocalDateTime.now());
        erpPro.setProId(idGenService.getSeqId("pro_id"));
        erpPro.setEmpId(SecurityUtils.getEmpId());
        return erpProMapper.insert(erpPro);
    }

    /**
     * 新增服装产品管理
     * @param erpProDTO 服装产品管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertAll(ErpProDTO erpProDTO) {
        ErpPro erpPro = ConvertUtils.convert(erpProDTO, ErpPro.class);
        erpPro.setDelFlag(0);
        erpPro.setCreateTime(LocalDateTime.now());
        erpPro.setProId(idGenService.getSeqId("pro_id"));
        erpPro.setEmpId(SecurityUtils.getEmpId());
        int num =erpProMapper.insert(erpPro);
        List<ErpProColor> proColorList = erpProDTO.getColorList();
        List<ErpProSize> proSizeList = erpProDTO.getSizeList();
        List<ErpProProcess> proProcList = erpProDTO.getProcList();
        if(null!=proColorList&& !proColorList.isEmpty()){
            for (int i = 0; i < proColorList.size(); i++) {
                ErpProColor  proColor = proColorList.get(i);
                proColor.setProId(erpPro.getProId());
                proColor.setSeqno((long) (i + 1));
                proColorService.insert(proColor);
            }
        }
        if(null!=proSizeList&& !proSizeList.isEmpty()){
            for (int i = 0; i < proSizeList.size(); i++) {
                ErpProSize  proSize = proSizeList.get(i);
                proSize.setProId(erpPro.getProId());
                proSize.setSeqNo((long) (i + 1));
                proSizeService.insert(proSize);
            }
        }
        if(null!=proProcList&& !proProcList.isEmpty()){
            for (int i = 0; i < proProcList.size(); i++) {
                ErpProProcess  proProcess = proProcList.get(i);
                proProcess.setProId(erpPro.getProId());
                proProcess.setSeqNo((long) (i + 1));
                proProcessService.insert(proProcess);
            }
        }
        return num;
    }


    /**
     * 修改服装产品管理
     *
     * @param erpPro 服装产品管理
     * @return 结果
     */
    public int update(ErpPro erpPro) {
        return erpProMapper.updateById(erpPro);
    }

    /**
     * 批量删除服装产品管理
     *
     * @param proIds 需要删除的服装产品管理主键
     * @return 结果
     */
    public int deleteByProIds(Long[] proIds) {
        return erpProMapper.updateDelFlagByIds(proIds);
    }

    /**
     * 删除服装产品管理信息
     *
     * @param proId 服装产品管理主键
     * @return 结果
     */
    public int deleteByProId(Long proId) {
        Long[] proIds = {proId};
        return erpProMapper.updateDelFlagByIds(proIds);
    }
}
