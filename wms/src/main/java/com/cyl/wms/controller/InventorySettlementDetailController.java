package com.cyl.wms.controller;

import java.util.List;

import com.cyl.wms.domain.entity.InventorySettlementDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cyl.wms.convert.InventorySettlementDetailConvert;
import com.cyl.wms.pojo.query.InventorySettlementDetailQuery;
import com.cyl.wms.service.InventorySettlementDetailService;
import com.cyl.wms.pojo.vo.InventorySettlementDetailVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 库存结算明细Controller
 *
 * @author zcc
 * @date 2024-03-15
 */
@Api(description ="库存结算明细接口列表")
@RestController
@RequestMapping("/wms/inventorySettlementDetail")
public class InventorySettlementDetailController extends BaseController {
    @Autowired
    private InventorySettlementDetailService service;
    @Autowired
    private InventorySettlementDetailConvert convert;

    @ApiOperation("查询库存结算明细列表")
    @PreAuthorize("@ss.hasPermi('wms:inventorySettlementDetail:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<InventorySettlementDetail>> list(@RequestBody InventorySettlementDetailQuery query, Pageable page) {
        List<InventorySettlementDetail> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出库存结算明细列表")
    @PreAuthorize("@ss.hasPermi('wms:inventorySettlementDetail:export')")
    @Log(title = "库存结算明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(InventorySettlementDetailQuery query) {
        List<InventorySettlementDetail> list = service.selectList(query, null);
        ExcelUtil<InventorySettlementDetailVO> util = new ExcelUtil<>(InventorySettlementDetailVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "库存结算明细数据"));
    }

    @ApiOperation("获取库存结算明细详细信息")
    @PreAuthorize("@ss.hasPermi('wms:inventorySettlementDetail:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<InventorySettlementDetail> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增库存结算明细")
    @PreAuthorize("@ss.hasPermi('wms:inventorySettlementDetail:add')")
    @Log(title = "库存结算明细", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody InventorySettlementDetail inventorySettlementDetail) {
        return ResponseEntity.ok(service.insert(inventorySettlementDetail));
    }

    @ApiOperation("修改库存结算明细")
    @PreAuthorize("@ss.hasPermi('wms:inventorySettlementDetail:edit')")
    @Log(title = "库存结算明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody InventorySettlementDetail inventorySettlementDetail) {
        return ResponseEntity.ok(service.update(inventorySettlementDetail));
    }

    @ApiOperation("删除库存结算明细")
    @PreAuthorize("@ss.hasPermi('wms:inventorySettlementDetail:remove')")
    @Log(title = "库存结算明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
