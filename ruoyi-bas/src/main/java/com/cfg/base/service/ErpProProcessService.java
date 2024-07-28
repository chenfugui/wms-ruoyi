package com.cfg.base.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProProcessMapper;
import com.cfg.base.domain.ErpProProcess;
import com.cfg.base.pojo.query.ErpProProcessQuery;

/**
 * 服装工序信息Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProProcessService {
    @Autowired
    private ErpProProcessMapper erpProProcessMapper;

    /**
     * 查询服装工序信息
     *
     * @param id 服装工序信息主键
     * @return 服装工序信息
     */
    public ErpProProcess selectById(Long id) {
        return erpProProcessMapper.selectById(id);
    }

    /**
     * 查询服装工序信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装工序信息
     */
    public List<ErpProProcess> selectList(ErpProProcessQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProProcess> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        Long empId = query.getEmpId();
        if (null!=empId) {
            qw.eq("emp_id", empId);
        }
        Long stepId = query.getStepId();
        if (stepId != null) {
            qw.eq("step_id", stepId);
        }
        String stepCode = query.getStepCode();
        if (!StringUtils.isEmpty(stepCode)) {
            qw.eq("step_code", stepCode);
        }
        String stepNameLike = query.getStepNameLike();
        if (!StringUtils.isEmpty(stepNameLike)) {
            qw.like("step_name", stepNameLike);
        }
        BigDecimal price = query.getPrice();
        if (price != null) {
            qw.eq("price", price);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        return erpProProcessMapper.selectList(qw);
    }

    /**
     * 新增服装工序信息
     *
     * @param erpProProcess 服装工序信息
     * @return 结果
     */
    public int insert(ErpProProcess erpProProcess) {
        erpProProcess.setDelFlag(0);
        erpProProcess.setCreateTime(LocalDateTime.now());
        return erpProProcessMapper.insert(erpProProcess);
    }

    /**
     * 修改服装工序信息
     *
     * @param erpProProcess 服装工序信息
     * @return 结果
     */
    public int update(ErpProProcess erpProProcess) {
        return erpProProcessMapper.updateById(erpProProcess);
    }

    /**
     * 批量删除服装工序信息
     *
     * @param ids 需要删除的服装工序信息主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProProcessMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装工序信息信息
     *
     * @param id 服装工序信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProProcessMapper.updateDelFlagByIds(ids);
    }
}
