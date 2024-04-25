package com.cyl.wms.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cyl.wms.domain.entity.InventorySettlementDetail;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cyl.wms.mapper.InventorySettlementDetailMapper;
import com.cyl.wms.pojo.query.InventorySettlementDetailQuery;

/**
 * 库存结算明细Service业务层处理
 *
 *
 * @author zcc
 */
@Service
public class InventorySettlementDetailService {
    @Autowired
    private InventorySettlementDetailMapper inventorySettlementDetailMapper;

    /**
     * 查询库存结算明细
     *
     * @param id 库存结算明细主键
     * @return 库存结算明细
     */
    public InventorySettlementDetail selectById(Long id) {
        return inventorySettlementDetailMapper.selectById(id);
    }

    /**
     * 查询库存结算明细列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 库存结算明细
     */
    public List<InventorySettlementDetail> selectList(InventorySettlementDetailQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<InventorySettlementDetail> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long settlementId = query.getSettlementId();
        if (settlementId != null) {
            qw.eq("settlement_id", settlementId);
        }
        Integer settlementType = query.getSettlementType();
        if (settlementType != null) {
            qw.eq("settlement_type", settlementType);
        }
        Long itemId = query.getItemId();
        if (itemId != null) {
            qw.eq("item_id", itemId);
        }
        String itemNo = query.getItemNo();
        if (!StringUtils.isEmpty(itemNo)) {
            qw.eq("item_no", itemNo);
        }
        String itemNameLike = query.getItemNameLike();
        if (!StringUtils.isEmpty(itemNameLike)) {
            qw.like("item_name", itemNameLike);
        }
        Long warehouseId = query.getWarehouseId();
        if (warehouseId != null) {
            qw.eq("warehouse_id", warehouseId);
        }
        String warehouseNo = query.getWarehouseNo();
        if (!StringUtils.isEmpty(warehouseNo)) {
            qw.eq("warehouse_no", warehouseNo);
        }
        String warehouseNameLike = query.getWarehouseNameLike();
        if (!StringUtils.isEmpty(warehouseNameLike)) {
            qw.like("warehouse_name", warehouseNameLike);
        }
        Long areaId = query.getAreaId();
        if (areaId != null) {
            qw.eq("area_id", areaId);
        }
        String areaNo = query.getAreaNo();
        if (!StringUtils.isEmpty(areaNo)) {
            qw.eq("area_no", areaNo);
        }
        String areaNameLike = query.getAreaNameLike();
        if (!StringUtils.isEmpty(areaNameLike)) {
            qw.like("area_name", areaNameLike);
        }
        BigDecimal previousBalance = query.getPreviousBalance();
        if (previousBalance != null) {
            qw.eq("previous_balance", previousBalance);
        }
        BigDecimal currentEnter = query.getCurrentEnter();
        if (currentEnter != null) {
            qw.eq("current_enter", currentEnter);
        }
        BigDecimal currentOut = query.getCurrentOut();
        if (currentOut != null) {
            qw.eq("current_out", currentOut);
        }
        BigDecimal currentCheck = query.getCurrentCheck();
        if (currentCheck != null) {
            qw.eq("current_check", currentCheck);
        }
        BigDecimal currentBalance = query.getCurrentBalance();
        if (currentBalance != null) {
            qw.eq("current_balance", currentBalance);
        }
        return inventorySettlementDetailMapper.selectList(qw);
    }

    /**
     * 新增库存结算明细
     *
     * @param inventorySettlementDetail 库存结算明细
     * @return 结果
     */
    public int insert(InventorySettlementDetail inventorySettlementDetail) {
        inventorySettlementDetail.setDelFlag(0);
        inventorySettlementDetail.setCreateTime(LocalDateTime.now());
        return inventorySettlementDetailMapper.insert(inventorySettlementDetail);
    }

    /**
     * 修改库存结算明细
     *
     * @param inventorySettlementDetail 库存结算明细
     * @return 结果
     */
    public int update(InventorySettlementDetail inventorySettlementDetail) {
        return inventorySettlementDetailMapper.updateById(inventorySettlementDetail);
    }

    /**
     * 批量删除库存结算明细
     *
     * @param ids 需要删除的库存结算明细主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return inventorySettlementDetailMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除库存结算明细信息
     *
     * @param id 库存结算明细主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return inventorySettlementDetailMapper.updateDelFlagByIds(ids);
    }
}
