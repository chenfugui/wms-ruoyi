package com.cfg.base.controller;

import java.util.List;

import com.cfg.base.pojo.dto.ErpProTemplateDTO;
import com.cfg.idgen.util.ConvertUtils;
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
import com.cfg.base.convert.ErpProTemplateConvert;
import com.cfg.base.domain.ErpProTemplate;
import com.cfg.base.pojo.query.ErpProTemplateQuery;
import com.cfg.base.service.ErpProTemplateService;
import com.cfg.base.pojo.vo.ErpProTemplateVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 模板表Controller
 *
 * @author chenfg
 * @date 2025-02-16
 */
@Api(description ="模板接口列表")
@RestController
@RequestMapping("/base/erpProTemplate")
public class ErpProTemplateController extends BaseController {
    @Autowired
    private ErpProTemplateService service;
    @Autowired
    private ErpProTemplateConvert convert;

    @ApiOperation("查询模板列表")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplate:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProTemplate>> list(@RequestBody ErpProTemplateQuery query, Pageable page) {
        List<ErpProTemplate> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出模板列表")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplate:export')")
    @Log(title = "模板表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProTemplateQuery query) {
        List<ErpProTemplate> list = service.selectList(query, null);
        ExcelUtil<ErpProTemplateVO> util = new ExcelUtil<>(ErpProTemplateVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "模板表数据"));
    }

    @ApiOperation("获取模板详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplate:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProTemplate> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增模板")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplate:add')")
    @Log(title = "模板表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProTemplateDTO erpProTemplate) {
        return ResponseEntity.ok(service.insert(erpProTemplate));
    }

    @ApiOperation("修改模板")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplate:edit')")
    @Log(title = "模板表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProTemplateDTO erpProTemplate) {
        return ResponseEntity.ok(service.update(erpProTemplate));
    }

    @ApiOperation("删除模板")
    @PreAuthorize("@ss.hasPermi('base:erpProTemplate:remove')")
    @Log(title = "模板表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
