package com.cyl.wms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.domain.entity.CustomerTransaction;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.CustomerTransactionMapper;
import com.cyl.wms.pojo.query.CustomerTransactionQuery;

/**
 * 客户账户流水Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class CustomerTransactionService {
    @Autowired
    private CustomerTransactionMapper customerTransactionMapper;

    /**
     * 查询客户账户流水
     *
     * @param id 客户账户流水主键
     * @return 客户账户流水
     */
    public CustomerTransaction selectById(Integer id) {
        return customerTransactionMapper.selectById(id);
    }

    /**
     * 查询客户账户流水列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 客户账户流水
     */
    public List<CustomerTransaction> selectList(CustomerTransactionQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<CustomerTransaction> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String transactionCode = query.getTransactionCode();
        if (!StringUtils.isEmpty(transactionCode)) {
            qw.eq("transaction_code", transactionCode);
        }
        String customerId = query.getCustomerId();
        if (!StringUtils.isEmpty(customerId)) {
            qw.eq("customer_id", customerId);
        }
        String transactionType = query.getTransactionType();
        if (!StringUtils.isEmpty(transactionType)) {
            qw.eq("transaction_type", transactionType);
        }
        BigDecimal transactionAmount = query.getTransactionAmount();
        if (transactionAmount != null) {
            qw.eq("transaction_amount", transactionAmount);
        }
        BigDecimal previousBalance = query.getPreviousBalance();
        if (previousBalance != null) {
            qw.eq("previous_balance", previousBalance);
        }
        BigDecimal currentBalance = query.getCurrentBalance();
        if (currentBalance != null) {
            qw.eq("current_balance", currentBalance);
        }
        Long shipmentOrderId = query.getShipmentOrderId();
        if (shipmentOrderId != null) {
            qw.eq("shipment_order_id", shipmentOrderId);
        }
        return customerTransactionMapper.selectList(qw);
    }

    /**
     * 新增客户账户流水
     *
     * @param customerTransaction 客户账户流水
     * @return 结果
     */
    public int insert(CustomerTransaction customerTransaction) {
        customerTransaction.setDelFlag(0);
        customerTransaction.setCreateTime(LocalDateTime.now());
        return customerTransactionMapper.insert(customerTransaction);
    }

    /**
     * 修改客户账户流水
     *
     * @param customerTransaction 客户账户流水
     * @return 结果
     */
    public int update(CustomerTransaction customerTransaction) {
        return customerTransactionMapper.updateById(customerTransaction);
    }

    /**
     * 批量删除客户账户流水
     *
     * @param ids 需要删除的客户账户流水主键
     * @return 结果
     */
    public int deleteByIds(Integer[] ids) {
        return customerTransactionMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除客户账户流水信息
     *
     * @param id 客户账户流水主键
     * @return 结果
     */
    public int deleteById(Integer id) {
        Integer[] ids = {id};
        return customerTransactionMapper.updateDelFlagByIds(ids);
    }
}
