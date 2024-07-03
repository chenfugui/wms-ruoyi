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
import com.cfg.base.convert.ErpProMakeBatchConvert;
import com.cfg.base.domain.ErpProMakeBatch;
import com.cfg.base.pojo.query.ErpProMakeBatchQuery;
import com.cfg.base.service.ErpProMakeBatchService;
import com.cfg.base.pojo.vo.ErpProMakeBatchVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装生产批次Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装生产批次接口列表")
@RestController
@RequestMapping("/base/erpProMakeBatch")
public class ErpProMakeBatchController extends BaseController {
    @Autowired
    private ErpProMakeBatchService service;
    @Autowired
    private ErpProMakeBatchConvert convert;

    @ApiOperation("查询服装生产批次列表")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeBatch:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProMakeBatch>> list(@RequestBody ErpProMakeBatchQuery query, Pageable page) {
        List<ErpProMakeBatch> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装生产批次列表")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeBatch:export')")
    @Log(title = "服装生产批次", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProMakeBatchQuery query) {
        List<ErpProMakeBatch> list = service.selectList(query, null);
        ExcelUtil<ErpProMakeBatchVO> util = new ExcelUtil<>(ErpProMakeBatchVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装生产批次数据"));
    }

    @ApiOperation("获取服装生产批次详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeBatch:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProMakeBatch> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装生产批次")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeBatch:add')")
    @Log(title = "服装生产批次", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProMakeBatch erpProMakeBatch) {
        return ResponseEntity.ok(service.insert(erpProMakeBatch));
    }

    @ApiOperation("修改服装生产批次")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeBatch:edit')")
    @Log(title = "服装生产批次", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProMakeBatch erpProMakeBatch) {
        return ResponseEntity.ok(service.update(erpProMakeBatch));
    }

    @ApiOperation("删除服装生产批次")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeBatch:remove')")
    @Log(title = "服装生产批次", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
