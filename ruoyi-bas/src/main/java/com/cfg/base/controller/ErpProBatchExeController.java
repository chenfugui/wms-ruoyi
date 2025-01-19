package com.cfg.base.controller;

import java.util.List;

import com.cfg.base.pojo.dto.ErpProBatchExeDTO;
import com.cfg.base.pojo.dto.ErpProMakeBatchDTO;
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
import com.cfg.base.convert.ErpProBatchExeConvert;
import com.cfg.base.domain.ErpProBatchExe;
import com.cfg.base.pojo.query.ErpProBatchExeQuery;
import com.cfg.base.service.ErpProBatchExeService;
import com.cfg.base.pojo.vo.ErpProBatchExeVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装生产进度Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装生产进度接口列表")
@RestController
@RequestMapping("/base/erpProBatchExe")
public class ErpProBatchExeController extends BaseController {
    @Autowired
    private ErpProBatchExeService service;
    @Autowired
    private ErpProBatchExeConvert convert;

    @ApiOperation("查询服装生产进度列表")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpProBatchExe>> list(@RequestBody ErpProBatchExeQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<ErpProBatchExe> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装生产进度列表")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:export')")
    @Log(title = "服装生产进度", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProBatchExeQuery query) {
        List<ErpProBatchExe> list = service.selectList(query, null);
        ExcelUtil<ErpProBatchExeVO> util = new ExcelUtil<>(ErpProBatchExeVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装生产进度数据"));
    }

    @ApiOperation("获取服装生产进度详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpProBatchExe> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增服装生产进度")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:add')")
    @Log(title = "服装生产进度", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpProBatchExe erpProBatchExe) {
        return ResponseEntity.ok(service.insert(erpProBatchExe));
    }

    @ApiOperation("修改服装生产进度")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:edit')")
    @Log(title = "服装生产进度", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpProBatchExe erpProBatchExe) {
        return ResponseEntity.ok(service.update(erpProBatchExe));
    }

    @ApiOperation("删除服装生产进度")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:remove')")
    @Log(title = "服装生产进度", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }

    @ApiOperation("新增服装生产进度")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:add')")
    @Log(title = "服装生产进度", businessType = BusinessType.INSERT)
    @PostMapping("/reg/makeRcd")
    public ResponseEntity<Integer> insertBatch(@RequestBody List<ErpProMakeBatchDTO> makeBatchDTOList) {
        return ResponseEntity.ok(service.insertBatch(makeBatchDTOList));
    }


    @ApiOperation("获取服装生产进度详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:query')")
    @PostMapping(value = "/scan/rcd")
    public ResponseEntity<List<ErpProBatchExeDTO>> getScanRcdList(@RequestBody ErpProBatchExeDTO batchExeDTO) {
        return ResponseEntity.ok(service.selectScanRcdList(batchExeDTO));
    }

    @ApiOperation("工资查询")
    @PreAuthorize("@ss.hasPermi('base:erpProBatchExe:query')")
    @PostMapping(value = "/salary")
    public ResponseEntity<List<ErpProBatchExeDTO>> getSalaryList(@RequestBody ErpProBatchExeDTO batchExeDTO) {
        return ResponseEntity.ok(service.selectSalaryList(batchExeDTO));
    }

}
