package com.cfg.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.domain.*;
import com.cfg.base.mapper.*;
import com.cfg.base.pojo.dto.ErpProDTO;
import com.cfg.base.pojo.query.ErpProColorQuery;
import com.cfg.base.pojo.query.ErpProProcessQuery;
import com.cfg.base.pojo.query.ErpProQuery;
import com.cfg.base.pojo.query.ErpProSizeQuery;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.ConvertUtils;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ErpProcService erpProcService;
    @Autowired
    private ErpProMakeService proMakeService;

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
            ErpProProcess proProcess = new ErpProProcess();
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
        OperatorUtils.setCreateInfo(erpPro);
        erpPro.setDelFlag(0);
        erpPro.setCreateTime(LocalDateTime.now());
        erpPro.setId(idGenService.getSeqId("pro_id"));
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
        ErpPro erpProQuery  = ConvertUtils.convert(erpProDTO, ErpPro.class);
        erpProQuery.setProCode(erpProDTO.getProCode());
        erpProQuery.setEmpId(SecurityUtils.getEmpId());
        List<ErpPro> dbProList = erpProMapper.selectByEntity(erpProQuery);
        if(CollectionUtils.isNotEmpty(dbProList)){
            throw new RuntimeException(erpProDTO.getProCode()+"产品编码重复");
        }
        ErpPro erpPro = ConvertUtils.convert(erpProDTO, ErpPro.class);
        erpPro.setDelFlag(0);
        erpPro.setStatus("0");
        erpPro.setCreateTime(LocalDateTime.now());
        erpPro.setId(idGenService.getSeqId("pro_id"));
        erpPro.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpPro);
        int num =erpProMapper.insert(erpPro);
        List<ErpProColor> proColorList = erpProDTO.getColorList();
        List<ErpProSize> proSizeList = erpProDTO.getSizeList();
        List<ErpProProcess> proProcList = erpProDTO.getProcList();
        if(null!=proColorList&& !proColorList.isEmpty()){
            for (int i = 0; i < proColorList.size(); i++) {
                ErpProColor  proColor = proColorList.get(i);
                proColor.setProId(erpPro.getId());
                proColor.setSeqno((long) (i + 1));
                proColorService.insert(proColor);
            }
        }
        if(null!=proSizeList&& !proSizeList.isEmpty()){
            for (int i = 0; i < proSizeList.size(); i++) {
                ErpProSize  proSize = proSizeList.get(i);
                proSize.setProId(erpPro.getId());
                proSize.setSeqNo((long) (i + 1));
                proSizeService.insert(proSize);
            }
        }
        if(null!=proProcList&& !proProcList.isEmpty()){
            for (int i = 0; i < proProcList.size(); i++) {
                ErpProProcess  proProcess = proProcList.get(i);
                proProcess.setProId(erpPro.getId());
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
        OperatorUtils.setUpdateInfo(erpPro);
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
     * @param proId 服装产品管理主键
     * @return 结果
     */
    public int deleteByProId(Long proId) {
        Long[] proIds = {proId};
        return erpProMapper.updateDelFlagByIds(proIds);
    }

    /**
     * 删除服装产品管理信息
     * @param proIds 服装产品管理主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int deleteByProIds(List<Long> proIds) {
        List<ErpProMake> makeList = proMakeService.selectMakeListByProIds(proIds);
        if(CollectionUtils.isNotEmpty(makeList)){
            throw new RuntimeException("存在裁床记录，不能删除");
        }else{
            if(null!=SecurityUtils.getEmpId()) {
                QueryWrapper<ErpPro> qw = new QueryWrapper<>();
                qw.in("id", proIds);
                qw.notIn("emp_id", SecurityUtils.getEmpId());
                Integer integer = erpProMapper.selectCount(qw);
                if (integer > 0) {
                    throw new RuntimeException("删除失败");
                }
            }
            //删除产品信息
            erpProMapper.deleteByProIds(proIds.toArray(new Long[0]));
            //删除产品工序
            proProcessService.deleteByProIds(proIds);
            //删除产品颜色
            proColorService.deleteByProIds(proIds);
            //删除产品尺寸
            proSizeService.deleteByProIds(proIds);
            //删除产品价格
            proPriceService.deleteByProIds(proIds);
        }
        return proIds.size();
    }

    /**
     * 根据服装产品主键获取颜色列表
     * @param proId
     * @return List<ErpProColor>
     */
    public List<ErpProColor> getColorList(Long proId){
        ErpProColorQuery query = new ErpProColorQuery();
        query.setProId(proId);
        return proColorService.selectList(query,null);
    }

    /**
     * 根据服装产品主键获取尺码列表
     * @param proId
     * @return List<ErpProSize>
     */
    public List<ErpProSize> getSizeList(Long proId){
        ErpProSizeQuery query = new ErpProSizeQuery();
        query.setProId(proId);
        return proSizeService.selectList(query,null);
    }

    /***
     * @author chenfg
     * @date: 2024/10/24 9:22
     * @description:  查询服装产品工序列表
     * @param proId
     * @return: java.util.List<com.cfg.base.domain.ErpProProcess>
     */
    public List<ErpProProcess> getProcessList(Long proId){
        ErpProProcessQuery query = new ErpProProcessQuery();
        query.setProId(proId);
        return proProcessService.selectList(query,null);
    }

    /***
     * @author chenfg
     * @date: 2024/10/24 9:23
     * @description:  获取产品工序Map
     * @param proId
     * @return: java.util.Map<java.lang.Long,com.cfg.base.domain.ErpProProcess>
     */
    public Map<Long,ErpProProcess> getProProcessMap(Long proId){
        List<ErpProProcess> proProcList = getProcessList(proId);
        Map<Long, ErpProProcess> proProcMap=new HashMap<>();
        if(CollectionUtils.isNotEmpty(proProcList)){
            for (ErpProProcess proProc : proProcList) {
                proProcMap.put(proProc.getId(),proProc);
            }
        }
        return proProcMap;
    }

    /**
     * 根据服装产品主键获取颜色Map
     * @param proId
     * @return Map<Long,ErpProColor>
     */
    public Map<Long, ErpProColor> getProColorMap(Long proId){
        List<ErpProColor> colors = getColorList(proId);
        Map<Long, ErpProColor> colorMap=new HashMap<>();
        if(CollectionUtils.isNotEmpty(colors)){
            for (ErpProColor color : colors) {
                colorMap.put(color.getId(),color);
            }
        }
        return colorMap;
    }

    /**
     * 根据服装产品主键获取尺码Map
     * @param proId
     * @return Map<Long,ErpProSize>
     */
    public Map<Long, ErpProSize> getProSizeMap(Long proId){
        List<ErpProSize> sizes = getSizeList(proId);
        Map<Long, ErpProSize> sizeMap=new HashMap<>();
        if(CollectionUtils.isNotEmpty(sizes)){
            for (ErpProSize size : sizes) {
                sizeMap.put(size.getId(),size);
            }
        }
        return sizeMap;
    }

    /**
     * 保存服装工序工价
     * @param erpProDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveProPrice(ErpProDTO erpProDTO) {
        Long proId = erpProDTO.getId();
        ErpPro erpPro = erpProMapper.selectById(proId);
        Assert.notNull(erpPro,"产品不存在");
        Assert.isTrue(erpPro.getEmpId().equals(SecurityUtils.getEmpId()),"产品不存在");
        List<ErpProPrice> proPriceList = erpProDTO.getPriceList();
        Assert.isTrue(CollectionUtils.isNotEmpty(proPriceList),"工价信息不能为空");
        if(CollectionUtils.isNotEmpty(proPriceList)){
            proPriceService.deleteByProId(proId);
        }
        for (ErpProPrice erpProPrice : proPriceList) {
            erpProPrice.setProId(proId);
            erpProPrice.setEmpId(SecurityUtils.getEmpId());
            Assert.isTrue(erpProPrice.getPrice().doubleValue()>=0,"工价不能小于0");
            proPriceService.insert(erpProPrice);
        }
    }
}
