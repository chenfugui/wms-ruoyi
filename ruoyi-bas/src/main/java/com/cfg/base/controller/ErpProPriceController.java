package com.cfg.base.controller;

import java.util.List;

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
import com.cfg.base.convert.ErpProPriceConvert;
import com.cfg.base.domain.ErpProPrice;
import com.cfg.base.pojo.query.ErpProPriceQuery;
import com.cfg.base.service.ErpProPriceService;
import com.cfg.base.pojo.vo.ErpProPriceVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装工价信息Controller
 *
 * @author chenfg
 * @date 2024-06-30
 */
@Api(description ="服装工价信息接口列表")
@RestController
@RequestMapping("/base/erpProPrice")
public class ErpProPriceController extends BaseController {
    @Autowired
    private ErpProPriceService service;
    @Autowired
    private ErpProPriceConvert convert;

    @ApiOperation("查询服装工价信息列表")
    @PreAuthorize("@ss.hasPermi('base:erpProPrice:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProPrice>> list(@RequestBody ErpProPriceQuery query, Pageable page) {
        List<ErpProPrice> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装工价信息列表")
    @PreAuthorize("@ss.hasPermi('base:erpProPrice:export')")
    @Log(title = "服装工价信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProPriceQuery query) {
        List<ErpProPrice> list = service.selectList(query, null);
        ExcelUtil<ErpProPriceVO> util = new ExcelUtil<>(ErpProPriceVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装工价信息数据"));
    }

    @ApiOperation("获取服装工价信息详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProPrice:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProPrice> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装工价信息")
    @PreAuthorize("@ss.hasPermi('base:erpProPrice:add')")
    @Log(title = "服装工价信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProPrice erpProPrice) {
        return ResponseEntity.ok(service.insert(erpProPrice));
    }

    @ApiOperation("修改服装工价信息")
    @PreAuthorize("@ss.hasPermi('base:erpProPrice:edit')")
    @Log(title = "服装工价信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProPrice erpProPrice) {
        return ResponseEntity.ok(service.update(erpProPrice));
    }

    @ApiOperation("删除服装工价信息")
    @PreAuthorize("@ss.hasPermi('base:erpProPrice:remove')")
    @Log(title = "服装工价信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
