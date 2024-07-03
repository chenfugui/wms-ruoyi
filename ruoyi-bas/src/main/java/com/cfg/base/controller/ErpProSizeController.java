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
import com.cfg.base.convert.ErpProSizeConvert;
import com.cfg.base.domain.ErpProSize;
import com.cfg.base.pojo.query.ErpProSizeQuery;
import com.cfg.base.service.ErpProSizeService;
import com.cfg.base.pojo.vo.ErpProSizeVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装生产尺码Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装生产尺码接口列表")
@RestController
@RequestMapping("/base/erpProSize")
public class ErpProSizeController extends BaseController {
    @Autowired
    private ErpProSizeService service;
    @Autowired
    private ErpProSizeConvert convert;

    @ApiOperation("查询服装生产尺码列表")
    @PreAuthorize("@ss.hasPermi('base:erpProSize:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProSize>> list(@RequestBody ErpProSizeQuery query, Pageable page) {
        List<ErpProSize> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装生产尺码列表")
    @PreAuthorize("@ss.hasPermi('base:erpProSize:export')")
    @Log(title = "服装生产尺码", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProSizeQuery query) {
        List<ErpProSize> list = service.selectList(query, null);
        ExcelUtil<ErpProSizeVO> util = new ExcelUtil<>(ErpProSizeVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装生产尺码数据"));
    }

    @ApiOperation("获取服装生产尺码详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProSize:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProSize> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装生产尺码")
    @PreAuthorize("@ss.hasPermi('base:erpProSize:add')")
    @Log(title = "服装生产尺码", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProSize erpProSize) {
        return ResponseEntity.ok(service.insert(erpProSize));
    }

    @ApiOperation("修改服装生产尺码")
    @PreAuthorize("@ss.hasPermi('base:erpProSize:edit')")
    @Log(title = "服装生产尺码", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProSize erpProSize) {
        return ResponseEntity.ok(service.update(erpProSize));
    }

    @ApiOperation("删除服装生产尺码")
    @PreAuthorize("@ss.hasPermi('base:erpProSize:remove')")
    @Log(title = "服装生产尺码", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
