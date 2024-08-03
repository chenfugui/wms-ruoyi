package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProMakeBatchMapper;
import com.cfg.base.domain.ErpProMakeBatch;
import com.cfg.base.pojo.query.ErpProMakeBatchQuery;

/**
 * 服装生产批次Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProMakeBatchService {
    @Autowired
    private ErpProMakeBatchMapper erpProMakeBatchMapper;

    @Autowired
    private IdGenService idGenService;

    /**
     * 查询服装生产批次
     *
     * @param id 服装生产批次主键
     * @return 服装生产批次
     */
    public ErpProMakeBatch selectById(Long id) {
        return erpProMakeBatchMapper.selectById(id);
    }

    /**
     * 查询服装生产批次列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装生产批次
     */
    public List<ErpProMakeBatch> selectList(ErpProMakeBatchQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProMakeBatch> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proMakeId = query.getProMakeId();
        if (proMakeId != null) {
            qw.eq("pro_make_id", proMakeId);
        }
        String proMakeNo = query.getProMakeNo();
        if (!StringUtils.isEmpty(proMakeNo)) {
            qw.eq("pro_make_no", proMakeNo);
        }
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        Long sizeId = query.getSizeId();
        if (sizeId != null) {
            qw.eq("size_id", sizeId);
        }
        String batchNo = query.getBatchNo();
        if (!StringUtils.isEmpty(batchNo)) {
            qw.eq("batch_no", batchNo);
        }
        String sizeNameLike = query.getSizeNameLike();
        if (!StringUtils.isEmpty(sizeNameLike)) {
            qw.like("size_name", sizeNameLike);
        }
        String sizeCode = query.getSizeCode();
        if (!StringUtils.isEmpty(sizeCode)) {
            qw.eq("size_code", sizeCode);
        }
        Long colorId = query.getColorId();
        if (colorId != null) {
            qw.eq("color_id", colorId);
        }
        String colorCode = query.getColorCode();
        if (!StringUtils.isEmpty(colorCode)) {
            qw.eq("color_code", colorCode);
        }
        String colorNameLike = query.getColorNameLike();
        if (!StringUtils.isEmpty(colorNameLike)) {
            qw.like("color_name", colorNameLike);
        }
        Long makeNum = query.getMakeNum();
        if (makeNum != null) {
            qw.eq("make_num", makeNum);
        }
        String bedNo = query.getBedNo();
        if (!StringUtils.isEmpty(bedNo)) {
            qw.eq("bed_no", bedNo);
        }
        Long pkgStartNo = query.getPkgStartNo();
        if (pkgStartNo != null) {
            qw.eq("pkg_start_no", pkgStartNo);
        }
        Long pkgEndNo = query.getPkgEndNo();
        if (pkgEndNo != null) {
            qw.eq("pkg_end_no", pkgEndNo);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        return erpProMakeBatchMapper.selectList(qw);
    }

    /**
     * 新增服装生产批次
     *
     * @param erpProMakeBatch 服装生产批次
     * @return 结果
     */
    public int insert(ErpProMakeBatch erpProMakeBatch) {
        erpProMakeBatch.setDelFlag(0);
        erpProMakeBatch.setCreateTime(LocalDateTime.now());
        erpProMakeBatch.setId(idGenService.getSeqId("batch_id"));
        erpProMakeBatch.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpProMakeBatch);
        return erpProMakeBatchMapper.insert(erpProMakeBatch);
    }

    /**
     * 修改服装生产批次
     *
     * @param erpProMakeBatch 服装生产批次
     * @return 结果
     */
    public int update(ErpProMakeBatch erpProMakeBatch) {
        OperatorUtils.setUpdateInfo(erpProMakeBatch);
        return erpProMakeBatchMapper.updateById(erpProMakeBatch);
    }

    /**
     * 批量删除服装生产批次
     *
     * @param ids 需要删除的服装生产批次主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProMakeBatchMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装生产批次信息
     *
     * @param id 服装生产批次主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProMakeBatchMapper.updateDelFlagByIds(ids);
    }
}
