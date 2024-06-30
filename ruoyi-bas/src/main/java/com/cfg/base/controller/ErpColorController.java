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
import com.cfg.base.convert.ErpColorConvert;
import com.cfg.base.domain.ErpColor;
import com.cfg.base.pojo.query.ErpColorQuery;
import com.cfg.base.service.ErpColorService;
import com.cfg.base.pojo.vo.ErpColorVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装颜色管理Controller
 *
 * @author chenfg
 * @date 2024-06-30
 */
@Api(description ="服装颜色管理接口列表")
@RestController
@RequestMapping("/base/erpColor")
public class ErpColorController extends BaseController {
    @Autowired
    private ErpColorService service;
    @Autowired
    private ErpColorConvert convert;

    @ApiOperation("查询服装颜色管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpColor:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpColor>> list(@RequestBody ErpColorQuery query, Pageable page) {
        List<ErpColor> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装颜色管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpColor:export')")
    @Log(title = "服装颜色管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpColorQuery query) {
        List<ErpColor> list = service.selectList(query, null);
        ExcelUtil<ErpColorVO> util = new ExcelUtil<>(ErpColorVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装颜色管理数据"));
    }

    @ApiOperation("获取服装颜色管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpColor:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpColor> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装颜色管理")
    @PreAuthorize("@ss.hasPermi('base:erpColor:add')")
    @Log(title = "服装颜色管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpColor erpColor) {
        return ResponseEntity.ok(service.insert(erpColor));
    }

    @ApiOperation("修改服装颜色管理")
    @PreAuthorize("@ss.hasPermi('base:erpColor:edit')")
    @Log(title = "服装颜色管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpColor erpColor) {
        return ResponseEntity.ok(service.update(erpColor));
    }

    @ApiOperation("删除服装颜色管理")
    @PreAuthorize("@ss.hasPermi('base:erpColor:remove')")
    @Log(title = "服装颜色管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
