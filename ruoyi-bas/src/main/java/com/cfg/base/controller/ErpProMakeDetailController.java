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
import com.cfg.base.convert.ErpProMakeDetailConvert;
import com.cfg.base.domain.ErpProMakeDetail;
import com.cfg.base.pojo.query.ErpProMakeDetailQuery;
import com.cfg.base.service.ErpProMakeDetailService;
import com.cfg.base.pojo.vo.ErpProMakeDetailVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装生产明细Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装生产明细接口列表")
@RestController
@RequestMapping("/base/erpProMakeDetail")
public class ErpProMakeDetailController extends BaseController {
    @Autowired
    private ErpProMakeDetailService service;
    @Autowired
    private ErpProMakeDetailConvert convert;

    @ApiOperation("查询服装生产明细列表")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeDetail:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProMakeDetail>> list(@RequestBody ErpProMakeDetailQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<ErpProMakeDetail> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装生产明细列表")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeDetail:export')")
    @Log(title = "服装生产明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProMakeDetailQuery query) {
        List<ErpProMakeDetail> list = service.selectList(query, null);
        ExcelUtil<ErpProMakeDetailVO> util = new ExcelUtil<>(ErpProMakeDetailVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装生产明细数据"));
    }

    @ApiOperation("获取服装生产明细详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeDetail:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProMakeDetail> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装生产明细")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeDetail:add')")
    @Log(title = "服装生产明细", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProMakeDetail erpProMakeDetail) {
        return ResponseEntity.ok(service.insert(erpProMakeDetail));
    }

    @ApiOperation("修改服装生产明细")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeDetail:edit')")
    @Log(title = "服装生产明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProMakeDetail erpProMakeDetail) {
        return ResponseEntity.ok(service.update(erpProMakeDetail));
    }

    @ApiOperation("删除服装生产明细")
    @PreAuthorize("@ss.hasPermi('base:erpProMakeDetail:remove')")
    @Log(title = "服装生产明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
