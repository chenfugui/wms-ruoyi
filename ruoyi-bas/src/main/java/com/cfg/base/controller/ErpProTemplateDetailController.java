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
import com.cfg.base.convert.ErpProTemplateDetailConvert;
import com.cfg.base.domain.ErpProTemplateDetail;
import com.cfg.base.pojo.query.ErpProTemplateDetailQuery;
import com.cfg.base.service.ErpProTemplateDetailService;
import com.cfg.base.pojo.vo.ErpProTemplateDetailVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 模板项目明细管理Controller
 *
 * @author chenfg
 * @date 2025-02-16
 */
@Api(description ="模板项目明细管理接口列表")
@RestController
@RequestMapping("/base/erpProTemplateDetail")
public class ErpProTemplateDetailController extends BaseController {
    @Autowired
    private ErpProTemplateDetailService service;
    @Autowired
    private ErpProTemplateDetailConvert convert;

    @ApiOperation("查询模板项目列表")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplateDetail:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProTemplateDetail>> list(@RequestBody ErpProTemplateDetailQuery query, Pageable page) {
        List<ErpProTemplateDetail> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出模板项目列表")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplateDetail:export')")
    @Log(title = "模板项目明细管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProTemplateDetailQuery query) {
        List<ErpProTemplateDetail> list = service.selectList(query, null);
        ExcelUtil<ErpProTemplateDetailVO> util = new ExcelUtil<>(ErpProTemplateDetailVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "模板项目明细管理数据"));
    }

    @ApiOperation("获取模板项目信息")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplateDetail:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProTemplateDetail> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增模板项目明细")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplateDetail:add')")
    @Log(title = "模板项目明细管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProTemplateDetail erpProTemplateDetail) {
        return ResponseEntity.ok(service.insert(erpProTemplateDetail));
    }

    @ApiOperation("修改模板项目明细")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplateDetail:edit')")
    @Log(title = "模板项目明细管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProTemplateDetail erpProTemplateDetail) {
        return ResponseEntity.ok(service.update(erpProTemplateDetail));
    }

    @ApiOperation("删除模板项目明细")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplateDetail:remove')")
    @Log(title = "模板项目明细管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
