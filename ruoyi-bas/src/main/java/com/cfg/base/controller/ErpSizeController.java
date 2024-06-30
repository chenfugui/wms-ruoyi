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
import com.cfg.base.convert.ErpSizeConvert;
import com.cfg.base.domain.ErpSize;
import com.cfg.base.pojo.query.ErpSizeQuery;
import com.cfg.base.service.ErpSizeService;
import com.cfg.base.pojo.vo.ErpSizeVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装尺码管理Controller
 *
 * @author chenfg
 * @date 2024-06-30
 */
@Api(description ="服装尺码管理接口列表")
@RestController
@RequestMapping("/base/erpSize")
public class ErpSizeController extends BaseController {
    @Autowired
    private ErpSizeService service;
    @Autowired
    private ErpSizeConvert convert;

    @ApiOperation("查询服装尺码管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpSize:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpSize>> list(@RequestBody ErpSizeQuery query, Pageable page) {
        List<ErpSize> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装尺码管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpSize:export')")
    @Log(title = "服装尺码管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpSizeQuery query) {
        List<ErpSize> list = service.selectList(query, null);
        ExcelUtil<ErpSizeVO> util = new ExcelUtil<>(ErpSizeVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装尺码管理数据"));
    }

    @ApiOperation("获取服装尺码管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpSize:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpSize> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装尺码管理")
    @PreAuthorize("@ss.hasPermi('base:erpSize:add')")
    @Log(title = "服装尺码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpSize erpSize) {
        return ResponseEntity.ok(service.insert(erpSize));
    }

    @ApiOperation("修改服装尺码管理")
    @PreAuthorize("@ss.hasPermi('base:erpSize:edit')")
    @Log(title = "服装尺码管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpSize erpSize) {
        return ResponseEntity.ok(service.update(erpSize));
    }

    @ApiOperation("删除服装尺码管理")
    @PreAuthorize("@ss.hasPermi('base:erpSize:remove')")
    @Log(title = "服装尺码管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
