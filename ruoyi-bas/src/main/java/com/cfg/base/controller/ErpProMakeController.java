package com.cfg.base.controller;

import java.util.List;

import com.cfg.base.dto.ProMakeDTO;
import com.cfg.base.dto.ProMakePrintDTO;
import com.cfg.idgen.util.WrapperResponse;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.BusinessType;
import com.cfg.base.convert.ErpProMakeConvert;
import com.cfg.base.domain.ErpProMake;
import com.cfg.base.pojo.query.ErpProMakeQuery;
import com.cfg.base.service.ErpProMakeService;
import com.cfg.base.pojo.vo.ErpProMakeVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装生产管理Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装生产管理接口列表")
@RestController
@RequestMapping("/base/erpProMake")
public class ErpProMakeController extends BaseController {
    @Autowired
    private ErpProMakeService service;
    @Autowired
    private ErpProMakeConvert convert;

    @ApiOperation("查询服装生产管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProMake>> list(@RequestBody ErpProMakeQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<ErpProMake> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装生产管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:export')")
    @Log(title = "服装生产管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProMakeQuery query) {
        List<ErpProMake> list = service.selectList(query, null);
        ExcelUtil<ErpProMakeVO> util = new ExcelUtil<>(ErpProMakeVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装生产管理数据"));
    }

    @ApiOperation("获取服装生产管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProMake> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装生产管理")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:add')")
    @Log(title = "服装生产管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProMake erpProMake) {
        return ResponseEntity.ok(service.insert(erpProMake));
    }

    @ApiOperation("修改服装生产管理")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:edit')")
    @Log(title = "服装生产管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProMake erpProMake) {
        return ResponseEntity.ok(service.update(erpProMake));
    }

    @ApiOperation("删除服装生产管理")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:remove')")
    @Log(title = "服装生产管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }

    @ApiOperation("新增服装生产管理")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:add')")
    @Log(title = "服装生产管理", businessType = BusinessType.INSERT)
    @PostMapping("/addAll")
    public WrapperResponse<ProMakeDTO> addALL(@RequestBody ProMakeDTO proMakeDTO) {
        proMakeDTO = service.insertAll(proMakeDTO);
        return WrapperResponse.success(proMakeDTO);
    }

    @ApiOperation("查询服装生产信息")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:query')")
    @GetMapping("/getMakeDetail")
    public ResponseEntity<ProMakeDTO> getMakeDetail(@RequestParam("makeId") Long makeId) {
        ProMakeDTO proMakeDTO = service.selectByDetailId(makeId);
        return ResponseEntity.ok(proMakeDTO);
    }

    @ApiOperation("查询服装生产历史记录")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:query')")
    @PostMapping("/proMakeHis")
    public ResponseEntity<Page<ProMakePrintDTO>> getProMakeHis(@RequestBody ErpProMakeQuery query, Pageable page) {
        List<ProMakePrintDTO> mkPrintList= service.selectCutHisList(query,page);
        return ResponseEntity.ok(new PageImpl<>(mkPrintList, page, ((com.github.pagehelper.Page)mkPrintList).getTotal()));
    }

    @ApiOperation("删除服装生产记录")
    @PreAuthorize("@ss.hasPermi('base:erpProMake:remove')")
    @Log(title = "服装生产管理", businessType = BusinessType.DELETE)
    @PostMapping("/del")
    public ResponseEntity<Integer> deleteByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(service.deleteMakeByIds(ids));
    }
}
