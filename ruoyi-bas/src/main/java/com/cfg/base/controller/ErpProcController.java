package com.cfg.base.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.cfg.base.convert.ErpProcConvert;
import com.cfg.base.domain.ErpProc;
import com.cfg.base.pojo.query.ErpProcQuery;
import com.cfg.base.service.ErpProcService;
import com.cfg.base.pojo.vo.ErpProcVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装工序管理Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装工序管理接口列表")
@RestController
@RequestMapping("/base/erpProc")
public class ErpProcController extends BaseController {
    @Autowired
    private ErpProcService service;
    @Autowired
    private ErpProcConvert convert;

    @ApiOperation("查询服装工序管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpProc:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProc>> list(@RequestBody ErpProcQuery query, Pageable page) {
        List<ErpProc> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装工序管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpProc:export')")
    @Log(title = "服装工序管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProcQuery query) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<ErpProc> list = service.selectList(query, null);
        ExcelUtil<ErpProcVO> util = new ExcelUtil<>(ErpProcVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装工序管理数据"));
    }

    @ApiOperation("获取服装工序管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProc:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProc> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装工序管理")
    @PreAuthorize("@ss.hasPermi('base:erpProc:add')")
    @Log(title = "服装工序管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProc erpProc) {
        return ResponseEntity.ok(service.insert(erpProc));
    }

    @ApiOperation("修改服装工序管理")
    @PreAuthorize("@ss.hasPermi('base:erpProc:edit')")
    @Log(title = "服装工序管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProc erpProc) {
        return ResponseEntity.ok(service.update(erpProc));
    }

    @ApiOperation("删除服装工序管理")
    @PreAuthorize("@ss.hasPermi('base:erpProc:remove')")
    @Log(title = "服装工序管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
