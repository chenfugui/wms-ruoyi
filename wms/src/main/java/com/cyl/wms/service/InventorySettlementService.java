package com.cyl.wms.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.domain.entity.InventorySettlement;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.InventorySettlementMapper;
import com.cyl.wms.pojo.query.InventorySettlementQuery;

/**
 * 库存结算单Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class InventorySettlementService {
    @Autowired
    private InventorySettlementMapper inventorySettlementMapper;

    /**
     * 查询库存结算单
     *
     * @param id 库存结算单主键
     * @return 库存结算单
     */
    public InventorySettlement selectById(Long id) {
        return inventorySettlementMapper.selectById(id);
    }

    /**
     * 查询库存结算单列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 库存结算单
     */
    public List<InventorySettlement> selectList(InventorySettlementQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<InventorySettlement> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String inventorySettlementNo = query.getInventorySettlementNo();
        if (!StringUtils.isEmpty(inventorySettlementNo)) {
            qw.eq("inventory_settlement_no", inventorySettlementNo);
        }
        Integer inventorySettlementStatus = query.getInventorySettlementStatus();
        if (inventorySettlementStatus != null) {
            qw.eq("inventory_settlement_status", inventorySettlementStatus);
        }
        LocalDateTime inventorySettlementStartTime = query.getInventorySettlementStartTime();
        if (inventorySettlementStartTime != null) {
            qw.eq("inventory_settlement_start_time", inventorySettlementStartTime);
        }
        LocalDateTime inventorySettlementEndTime = query.getInventorySettlementEndTime();
        if (inventorySettlementEndTime != null) {
            qw.eq("inventory_settlement_end_time", inventorySettlementEndTime);
        }
        Integer settlementType = query.getSettlementType();
        if (settlementType != null) {
            qw.eq("settlement_type", settlementType);
        }
        return inventorySettlementMapper.selectList(qw);
    }

    /**
     * 新增库存结算单
     *
     * @param inventorySettlement 库存结算单
     * @return 结果
     */
    public int insert(InventorySettlement inventorySettlement) {
        inventorySettlement.setDelFlag(0);
        inventorySettlement.setCreateTime(LocalDateTime.now());
        return inventorySettlementMapper.insert(inventorySettlement);
    }

    /**
     * 修改库存结算单
     *
     * @param inventorySettlement 库存结算单
     * @return 结果
     */
    public int update(InventorySettlement inventorySettlement) {
        return inventorySettlementMapper.updateById(inventorySettlement);
    }

    /**
     * 批量删除库存结算单
     *
     * @param ids 需要删除的库存结算单主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return inventorySettlementMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存结算单信息
     *
     * @param id 库存结算单主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return inventorySettlementMapper.updateDelFlagByIds(ids);
    }
}
