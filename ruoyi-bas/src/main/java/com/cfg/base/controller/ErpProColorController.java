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
import com.cfg.base.convert.ErpProColorConvert;
import com.cfg.base.domain.ErpProColor;
import com.cfg.base.pojo.query.ErpProColorQuery;
import com.cfg.base.service.ErpProColorService;
import com.cfg.base.pojo.vo.ErpProColorVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 产品颜色管理Controller
 *
 * @author chenfg
 * @date 2024-07-28
 */
@Api(description ="产品颜色管理接口列表")
@RestController
@RequestMapping("/base/erpProColor")
public class ErpProColorController extends BaseController {
    @Autowired
    private ErpProColorService service;
    @Autowired
    private ErpProColorConvert convert;

    @ApiOperation("查询产品颜色管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpProColor:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProColor>> list(@RequestBody ErpProColorQuery query, Pageable page) {
        List<ErpProColor> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出产品颜色管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpProColor:export')")
    @Log(title = "产品颜色管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProColorQuery query) {
        List<ErpProColor> list = service.selectList(query, null);
        ExcelUtil<ErpProColorVO> util = new ExcelUtil<>(ErpProColorVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "产品颜色管理数据"));
    }

    @ApiOperation("获取产品颜色管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProColor:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProColor> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增产品颜色管理")
    @PreAuthorize("@ss.hasPermi('base:erpProColor:add')")
    @Log(title = "产品颜色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProColor erpProColor) {
        return ResponseEntity.ok(service.insert(erpProColor));
    }

    @ApiOperation("修改产品颜色管理")
    @PreAuthorize("@ss.hasPermi('base:erpProColor:edit')")
    @Log(title = "产品颜色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProColor erpProColor) {
        return ResponseEntity.ok(service.update(erpProColor));
    }

    @ApiOperation("删除产品颜色管理")
    @PreAuthorize("@ss.hasPermi('base:erpProColor:remove')")
    @Log(title = "产品颜色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
