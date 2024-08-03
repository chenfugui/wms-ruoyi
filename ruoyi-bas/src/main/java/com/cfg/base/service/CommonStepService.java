package com.cfg.base.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.CommonStepMapper;
import com.cfg.base.domain.CommonStep;
import com.cfg.base.pojo.query.CommonStepQuery;

/**
 * 工序信息Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class CommonStepService {
    @Autowired
    private CommonStepMapper commonStepMapper;

    /**
     * 查询工序信息
     *
     * @param stepId 工序信息主键
     * @return 工序信息
     */
    public CommonStep selectByStepId(Long stepId) {
        return commonStepMapper.selectById(stepId);
    }

    /**
     * 查询工序信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 工序信息
     */
    public List<CommonStep> selectList(CommonStepQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<CommonStep> qw = new QueryWrapper<>();
        qw.eq("del_flag","1");
        String stepCode = query.getStepCode();
        if (!StringUtils.isEmpty(stepCode)) {
            qw.eq("step_code", stepCode);
        }
        String stepNameLike = query.getStepNameLike();
        if (!StringUtils.isEmpty(stepNameLike)) {
            qw.like("step_name", stepNameLike);
        }
        BigDecimal stepPrice = query.getStepPrice();
        if (stepPrice != null) {
            qw.eq("step_price", stepPrice);
        }
        String empCode = query.getEmpCode();
        if (!StringUtils.isEmpty(empCode)) {
            qw.eq("emp_code", empCode);
        }
        Long stepSort = query.getStepSort();
        if (stepSort != null) {
            qw.eq("step_sort", stepSort);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        return commonStepMapper.selectList(qw);
    }

    /**
     * 新增工序信息
     *
     * @param commonStep 工序信息
     * @return 结果
     */
    public int insert(CommonStep commonStep) {
        commonStep.setDelFlag("1");
        commonStep.setCreateTime(LocalDateTime.now());
        OperatorUtils.setCreateInfo(commonStep);
        return commonStepMapper.insert(commonStep);
    }

    /**
     * 修改工序信息
     *
     * @param commonStep 工序信息
     * @return 结果
     */
    public int update(CommonStep commonStep) {
        OperatorUtils.setUpdateInfo(commonStep);
        return commonStepMapper.updateById(commonStep);
    }

    /**
     * 批量删除工序信息
     *
     * @param stepIds 需要删除的工序信息主键
     * @return 结果
     */
    public int deleteByStepIds(Long[] stepIds) {
        return commonStepMapper.updateDelFlagByIds(stepIds);
    }

    /**
     * 删除工序信息信息
     *
     * @param stepId 工序信息主键
     * @return 结果
     */
    public int deleteByStepId(Long stepId) {
        Long[] stepIds = {stepId};
        return commonStepMapper.updateDelFlagByIds(stepIds);
    }
}
