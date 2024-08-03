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
import com.cfg.base.mapper.CommonWkbillMapper;
import com.cfg.base.domain.CommonWkbill;
import com.cfg.base.pojo.query.CommonWkbillQuery;

/**
 * 工单信息Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class CommonWkbillService {
    @Autowired
    private CommonWkbillMapper commonWkbillMapper;

    /**
     * 查询工单信息
     *
     * @param wkbillId 工单信息主键
     * @return 工单信息
     */
    public CommonWkbill selectByWkbillId(Long wkbillId) {
        return commonWkbillMapper.selectById(wkbillId);
    }

    /**
     * 查询工单信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 工单信息
     */
    public List<CommonWkbill> selectList(CommonWkbillQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<CommonWkbill> qw = new QueryWrapper<>();
        qw.eq("del_flag","0");
        Long userId = query.getUserId();
        if (userId != null) {
            qw.eq("user_id", userId);
        }
        Long stepId = query.getStepId();
        if (stepId != null) {
            qw.eq("step_id", stepId);
        }
        Long mknum = query.getMknum();
        if (mknum != null) {
            qw.eq("mknum", mknum);
        }
        BigDecimal stepPrice = query.getStepPrice();
        if (stepPrice != null) {
            qw.eq("step_price", stepPrice);
        }
        BigDecimal payable = query.getPayable();
        if (payable != null) {
            qw.eq("payable", payable);
        }
        BigDecimal actPay = query.getActPay();
        if (actPay != null) {
            qw.eq("act_pay", actPay);
        }
        String inputType = query.getInputType();
        if (!StringUtils.isEmpty(inputType)) {
            qw.eq("input_type", inputType);
        }
        return commonWkbillMapper.selectList(qw);
    }

    /**
     * 新增工单信息
     *
     * @param commonWkbill 工单信息
     * @return 结果
     */
    public int insert(CommonWkbill commonWkbill) {
        commonWkbill.setDelFlag("0");
        commonWkbill.setCreateTime(LocalDateTime.now());
        OperatorUtils.setCreateInfo(commonWkbill);
        return commonWkbillMapper.insert(commonWkbill);
    }

    /**
     * 修改工单信息
     *
     * @param commonWkbill 工单信息
     * @return 结果
     */
    public int update(CommonWkbill commonWkbill) {
        OperatorUtils.setUpdateInfo(commonWkbill);
        return commonWkbillMapper.updateById(commonWkbill);
    }

    /**
     * 批量删除工单信息
     *
     * @param wkbillIds 需要删除的工单信息主键
     * @return 结果
     */
    public int deleteByWkbillIds(Long[] wkbillIds) {
        return commonWkbillMapper.updateDelFlagByIds(wkbillIds);
    }

    /**
     * 删除工单信息信息
     *
     * @param wkbillId 工单信息主键
     * @return 结果
     */
    public int deleteByWkbillId(Long wkbillId) {
        Long[] wkbillIds = {wkbillId};
        return commonWkbillMapper.updateDelFlagByIds(wkbillIds);
    }
}
