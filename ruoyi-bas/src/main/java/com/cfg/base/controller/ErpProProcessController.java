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
import com.cfg.base.convert.ErpProProcessConvert;
import com.cfg.base.domain.ErpProProcess;
import com.cfg.base.pojo.query.ErpProProcessQuery;
import com.cfg.base.service.ErpProProcessService;
import com.cfg.base.pojo.vo.ErpProProcessVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装工序信息Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装工序信息接口列表")
@RestController
@RequestMapping("/base/erpProProcess")
public class ErpProProcessController extends BaseController {
    @Autowired
    private ErpProProcessService service;
    @Autowired
    private ErpProProcessConvert convert;

    @ApiOperation("查询服装工序信息列表")
    @PreAuthorize("@ss.hasPermi('base:erpProProcess:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProProcess>> list(@RequestBody ErpProProcessQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<ErpProProcess> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装工序信息列表")
    @PreAuthorize("@ss.hasPermi('base:erpProProcess:export')")
    @Log(title = "服装工序信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProProcessQuery query) {
        List<ErpProProcess> list = service.selectList(query, null);
        ExcelUtil<ErpProProcessVO> util = new ExcelUtil<>(ErpProProcessVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装工序信息数据"));
    }

    @ApiOperation("获取服装工序信息详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProProcess:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProProcess> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装工序信息")
    @PreAuthorize("@ss.hasPermi('base:erpProProcess:add')")
    @Log(title = "服装工序信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProProcess erpProProcess) {
        return ResponseEntity.ok(service.insert(erpProProcess));
    }

    @ApiOperation("修改服装工序信息")
    @PreAuthorize("@ss.hasPermi('base:erpProProcess:edit')")
    @Log(title = "服装工序信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProProcess erpProProcess) {
        return ResponseEntity.ok(service.update(erpProProcess));
    }

    @ApiOperation("删除服装工序信息")
    @PreAuthorize("@ss.hasPermi('base:erpProProcess:remove')")
    @Log(title = "服装工序信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
