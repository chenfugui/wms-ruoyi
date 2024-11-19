package com.cfg.base.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.domain.*;
import com.cfg.base.dto.ProMakeDTO;
import com.cfg.base.dto.ProMakeDetailDTO;
import com.cfg.base.dto.ProMakePrintDTO;
import com.cfg.base.mapper.ErpProMakeBatchMapper;
import com.cfg.base.pojo.dto.ErpProMakeDetailDTO;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.ConvertUtils;
import com.cfg.idgen.util.OperatorUtils;
import com.cfg.idgen.util.ReflectUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProMakeMapper;
import com.cfg.base.pojo.query.ErpProMakeQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服装生产管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProMakeService {
    @Autowired
    private ErpProMakeMapper erpProMakeMapper;

    @Autowired
    private IdGenService idGenService;

    @Autowired
    private ErpProMakeDetailService proMakeDetailService;
    @Autowired
    private ErpProMakeBatchMapper makeBatchMapper;
    @Autowired
    private ErpProMakeDetailService erpProMakeDetailService;
    @Autowired
    private ErpProService proService;

    /**
     * 查询服装生产管理
     *
     * @param id 服装生产管理主键
     * @return 服装生产管理
     */
    public ErpProMake selectById(Long id) {
        return erpProMakeMapper.selectById(id);
    }

    /***
     * @author chenfg
     * @date: 2024/11/19 16:14
     * @description:  查询生产详情
     * @param id 生产Id
     * @return: com.cfg.base.dto.ProMakeDTO
     */
    public ProMakeDTO selectByDetailId(Long id) {
        ErpProMake promake = erpProMakeMapper.selectById(id);
        List<ErpProMakeDetail> details = proMakeDetailService.selectDetailsByMakeId(promake.getId());
        ProMakeDTO proMakeDto = ConvertUtils.convert(promake, ProMakeDTO.class);
        List<ProMakeDetailDTO> detailDtoList = ConvertUtils.convert(details, ProMakeDetailDTO.class);
        proMakeDto.setMakeDetailList(detailDtoList);
        return proMakeDto;
    }

    /**
     * 查询服装生产管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装生产管理
     */
    public List<ErpProMake> selectList(ErpProMakeQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProMake> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        String proMakeNo = query.getProMakeNo();
        if (!StringUtils.isEmpty(proMakeNo)) {
            qw.eq("pro_make_no", proMakeNo);
        }
        LocalDateTime makeStartTime = query.getMakeStartTime();
        if (makeStartTime != null) {
            qw.eq("make_start_time", makeStartTime);
        }
        LocalDateTime makeEndTime = query.getMakeEndTime();
        if (makeEndTime != null) {
            qw.eq("make_end_time", makeEndTime);
        }
        LocalDateTime deliverTime = query.getDeliverTime();
        if (deliverTime != null) {
            qw.eq("deliver_time", deliverTime);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        return erpProMakeMapper.selectList(qw);
    }

    /**
     * 新增服装生产管理
     *
     * @param erpProMake 服装生产管理
     * @return 结果
     */
    public int insert(ErpProMake erpProMake) {
        erpProMake.setDelFlag(0);
        erpProMake.setCreateTime(LocalDateTime.now());
        erpProMake.setId(idGenService.getSeqId("make_id"));
        erpProMake.setEmpId(SecurityUtils.getEmpId());
        erpProMake.setSeqNo(idGenService.getSnowId());
        OperatorUtils.setCreateInfo(erpProMake);
        return erpProMakeMapper.insert(erpProMake);
    }


    /**
     * 新增服装生产管理
     * @param proMakeDTO 服装生产管理
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ProMakeDTO insertAll(ProMakeDTO proMakeDTO) {
        ErpProMake proMake = null;
        if(null!=proMakeDTO.getId()&&proMakeDTO.getId()>0){
            QueryWrapper<ErpProMakeBatch> wrapper = new QueryWrapper<>();
            wrapper.eq("pro_make_id",proMakeDTO.getId());
            List<ErpProMakeBatch> batches = makeBatchMapper.selectList(wrapper);
            if(CollectionUtils.isEmpty(batches)){
               erpProMakeMapper.deleteById(proMakeDTO.getId());
               proMakeDetailService.deleteByProMakeId(proMakeDTO.getId());

                proMake = erpProMakeMapper.selectById(proMakeDTO.getId());
                Assert.isTrue(null!=proMake,"生产信息不存在");
                ReflectUtils.copyPropertyValue(proMakeDTO,proMake,false);
                OperatorUtils.setUpdateInfo(proMake);
                erpProMakeMapper.updateById(proMake);

                //生产明细
                List<ProMakeDetailDTO> makeDetails = proMakeDTO.getMakeDetailList();
                if (CollectionUtils.isNotEmpty(makeDetails)) {
                    Map<Long, ErpProColor> colorMap = proService.getProColorMap(proMake.getProId());
                    Map<Long, ErpProSize> sizeMap = proService.getProSizeMap(proMake.getProId());
                    List<ErpProMakeDetail> details = ConvertUtils.convert(makeDetails, ErpProMakeDetail.class);
                    for (ErpProMakeDetail detail : details) {
                        detail.setProMakeId(proMake.getId());
                        detail.setProId(proMake.getProId());
                        ErpProColor proColor = colorMap.get(detail.getColorId());
                        detail.setColorCode(proColor.getColorCode());
                        detail.setColorName(proColor.getColorName());
                        ErpProSize proSize = sizeMap.get(detail.getSizeId());
                        detail.setSizeCode(proSize.getSizeCode());
                        detail.setSizeName(proSize.getSizeName());
                        Assert.isTrue(null != detail.getMakeNum() && detail.getMakeNum() >= 0, "数量必须大于0");
                        detail.setProMakeNo(proMake.getProMakeNo());
                        proMakeDetailService.insert(detail);
                    }
                }
            }else{
                throw new RuntimeException("已存在打菲记录");
            }
        }else {
            //保存生产信息
            proMake = ConvertUtils.convert(proMakeDTO, ErpProMake.class);
            proMake.setProMakeNo(String.valueOf(idGenService.getSeqId("make_no")));
            insert(proMake);
            //生产明细
            List<ProMakeDetailDTO> makeDetails = proMakeDTO.getMakeDetailList();
            if (CollectionUtils.isNotEmpty(makeDetails)) {
                Map<Long, ErpProColor> colorMap = proService.getProColorMap(proMake.getProId());
                Map<Long, ErpProSize> sizeMap = proService.getProSizeMap(proMake.getProId());
                List<ErpProMakeDetail> details = ConvertUtils.convert(makeDetails, ErpProMakeDetail.class);
                for (ErpProMakeDetail detail : details) {
                    detail.setProMakeId(proMake.getId());
                    detail.setProId(proMake.getProId());
                    ErpProColor proColor = colorMap.get(detail.getColorId());
                    detail.setColorCode(proColor.getColorCode());
                    detail.setColorName(proColor.getColorName());
                    ErpProSize proSize = sizeMap.get(detail.getSizeId());
                    detail.setSizeCode(proSize.getSizeCode());
                    detail.setSizeName(proSize.getSizeName());
                    Assert.isTrue(null != detail.getMakeNum() && detail.getMakeNum() >= 0, "数量必须大于0");
                    detail.setProMakeNo(proMake.getProMakeNo());
                    proMakeDetailService.insert(detail);
                }
            }
        }
        return ConvertUtils.convert(proMake,ProMakeDTO.class);
    }

    /**
     * 修改服装生产管理
     *
     * @param erpProMake 服装生产管理
     * @return 结果
     */
    public int update(ErpProMake erpProMake) {
        OperatorUtils.setUpdateInfo(erpProMake);
        return erpProMakeMapper.updateById(erpProMake);
    }

    /**
     * 批量删除服装生产管理
     *
     * @param ids 需要删除的服装生产管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProMakeMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装生产管理信息
     *
     * @param id 服装生产管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProMakeMapper.updateDelFlagByIds(ids);
    }

    /***
     * @author chenfg
     * @date: 2024/10/21 14:05
     * @description: 根据生产Id查询生产信息
     * @param makeId 生产Id
     * @return: com.cfg.base.dto.ProMakeDTO
     */
    public ProMakeDTO selectMakeDTOById(Long makeId) {
        ErpProMake proMake = erpProMakeMapper.selectById(makeId);
        Assert.notNull(proMake,"未查询到产品生产信息");
        List<ErpProMakeDetail> makeDetails =  erpProMakeDetailService.selectDetailsByMakeId(makeId);
        ProMakeDTO makeDTO = ConvertUtils.convert(proMake,ProMakeDTO.class);
        List<ProMakeDetailDTO> makeDetailDTOLst = ConvertUtils.convert(makeDetails,ProMakeDetailDTO.class);
        makeDTO.setMakeDetailList(makeDetailDTOLst);
        return makeDTO;
    }

    /***
     * @author chenfg
     * @date: 2024/11/19 15:08
     * @description:  查询产品裁床历史记录
     * @param query
     * @param page
     * @return: java.util.List<com.cfg.base.dto.ProMakePrintDTO>
     */
    public List<ProMakePrintDTO> selectCutHisList(ErpProMakeQuery query, Pageable page) {
        String sql ="select e.pro_id ,f.pro_name ,d.*,e.create_time  from erp_pro_make   e,(select b.pro_make_id ,b.bed_no  ,count(b.id) pkg_total_num,sum(b.make_num) mk_total_num from  erp_pro_make a,erp_pro_make_batch b where a.id=b.pro_make_id and a.pro_id ='1' group by b.pro_make_id,b.bed_no) d ,erp_pro f where e.id=d.pro_make_id and e.pro_id =f.id order by e.create_time desc";
        //return erpProMakeMapper.selectByEntity(query);
        return null;
    }

}
