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
import com.cfg.base.convert.MakeConvert;
import com.cfg.base.domain.Make;
import com.cfg.base.pojo.query.MakeQuery;
import com.cfg.base.service.MakeService;
import com.cfg.base.pojo.vo.MakeVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 产品生产表Controller
 *
 * @author chenfg
 * @date 2024-04-30
 */
@Api(description ="产品生产表接口列表")
@RestController
@RequestMapping("/base/make")
public class MakeController extends BaseController {
    @Autowired
    private MakeService service;
    @Autowired
    private MakeConvert convert;

    @ApiOperation("查询产品生产表列表")
    @PreAuthorize("@ss.hasPermi('base:make:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Make>> list(@RequestBody MakeQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<Make> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出产品生产表列表")
    @PreAuthorize("@ss.hasPermi('base:make:export')")
    @Log(title = "产品生产表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(MakeQuery query) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<Make> list = service.selectList(query, null);
        ExcelUtil<MakeVO> util = new ExcelUtil<>(MakeVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "产品生产表数据"));
    }

    @ApiOperation("获取产品生产表详细信息")
    @PreAuthorize("@ss.hasPermi('base:make:query')")
    @GetMapping(value = "/{makeId}")
    public ResponseEntity<Make> getInfo(@PathVariable("makeId") Long makeId) {
        return ResponseEntity.ok(service.selectByMakeId(makeId));
    }

    @ApiOperation("新增产品生产表")
    @PreAuthorize("@ss.hasPermi('base:make:add')")
    @Log(title = "产品生产表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Make make) {
        make.setEmpId(SecurityUtils.getEmpId());
        return ResponseEntity.ok(service.insert(make));
    }

    @ApiOperation("修改产品生产表")
    @PreAuthorize("@ss.hasPermi('base:make:edit')")
    @Log(title = "产品生产表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Make make) {
        make.setEmpId(null);
        return ResponseEntity.ok(service.update(make));
    }

    @ApiOperation("删除产品生产表")
    @PreAuthorize("@ss.hasPermi('base:make:remove')")
    @Log(title = "产品生产表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{makeIds}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] makeIds) {
        return ResponseEntity.ok(service.deleteByMakeIds(makeIds));
    }

    @ApiOperation("批量插入产品生产表")
    @PostMapping(value = "/gen")
    public ResponseEntity<List<Make>> addBatch(@RequestBody MakeQuery query) {
        query.setEmpId(SecurityUtils.getEmpId());
        return ResponseEntity.ok(service.insertBatch(query));
    }

}
