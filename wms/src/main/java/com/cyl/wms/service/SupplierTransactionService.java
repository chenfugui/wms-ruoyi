package com.cyl.wms.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.domain.entity.SupplierTransaction;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.SupplierTransactionMapper;
import com.cyl.wms.pojo.query.SupplierTransactionQuery;

/**
 * 供应商账户流水Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class SupplierTransactionService {
    @Autowired
    private SupplierTransactionMapper supplierTransactionMapper;

    /**
     * 查询供应商账户流水
     *
     * @param id 供应商账户流水主键
     * @return 供应商账户流水
     */
    public SupplierTransaction selectById(Integer id) {
        return supplierTransactionMapper.selectById(id);
    }

    /**
     * 查询供应商账户流水列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 供应商账户流水
     */
    public List<SupplierTransaction> selectList(SupplierTransactionQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<SupplierTransaction> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String transactionCode = query.getTransactionCode();
        if (!StringUtils.isEmpty(transactionCode)) {
            qw.eq("transaction_code", transactionCode);
        }
        String supplierId = query.getSupplierId();
        if (!StringUtils.isEmpty(supplierId)) {
            qw.eq("supplier_id", supplierId);
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
        Long receiptOrderId = query.getReceiptOrderId();
        if (receiptOrderId != null) {
            qw.eq("receipt_order_id", receiptOrderId);
        }
        return supplierTransactionMapper.selectList(qw);
    }

    /**
     * 新增供应商账户流水
     *
     * @param supplierTransaction 供应商账户流水
     * @return 结果
     */
    public int insert(SupplierTransaction supplierTransaction) {
        supplierTransaction.setDelFlag(0);
        supplierTransaction.setCreateTime(LocalDateTime.now());
        return supplierTransactionMapper.insert(supplierTransaction);
    }

    /**
     * 修改供应商账户流水
     *
     * @param supplierTransaction 供应商账户流水
     * @return 结果
     */
    public int update(SupplierTransaction supplierTransaction) {
        return supplierTransactionMapper.updateById(supplierTransaction);
    }

    /**
     * 批量删除供应商账户流水
     *
     * @param ids 需要删除的供应商账户流水主键
     * @return 结果
     */
    public int deleteByIds(Integer[] ids) {
        return supplierTransactionMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除供应商账户流水信息
     *
     * @param id 供应商账户流水主键
     * @return 结果
     */
    public int deleteById(Integer id) {
        Integer[] ids = {id};
        return supplierTransactionMapper.updateDelFlagByIds(ids);
    }
}
