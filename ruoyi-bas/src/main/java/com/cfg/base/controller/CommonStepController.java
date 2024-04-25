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
import com.cfg.base.convert.CommonStepConvert;
import com.cfg.base.domain.CommonStep;
import com.cfg.base.pojo.query.CommonStepQuery;
import com.cfg.base.service.CommonStepService;
import com.cfg.base.pojo.vo.CommonStepVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 工序信息Controller
 *
 * @author chenfg
 * @date 2024-04-25
 */
@Api(description ="工序信息接口列表")
@RestController
@RequestMapping("/base/commonStep")
public class CommonStepController extends BaseController {
    @Autowired
    private CommonStepService service;
    @Autowired
    private CommonStepConvert convert;

    @ApiOperation("查询工序信息列表")
    @PreAuthorize("@ss.hasPermi('base:commonStep:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<CommonStep>> list(@RequestBody CommonStepQuery query, Pageable page) {
        List<CommonStep> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出工序信息列表")
    @PreAuthorize("@ss.hasPermi('base:commonStep:export')")
    @Log(title = "工序信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(CommonStepQuery query) {
        List<CommonStep> list = service.selectList(query, null);
        ExcelUtil<CommonStepVO> util = new ExcelUtil<>(CommonStepVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "工序信息数据"));
    }

    @ApiOperation("获取工序信息详细信息")
    @PreAuthorize("@ss.hasPermi('base:commonStep:query')")
    @GetMapping(value = "/{stepId}")
    public ResponseEntity<CommonStep> getInfo(@PathVariable("stepId") Long stepId) {
        return ResponseEntity.ok(service.selectByStepId(stepId));
    }

    @ApiOperation("新增工序信息")
    @PreAuthorize("@ss.hasPermi('base:commonStep:add')")
    @Log(title = "工序信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody CommonStep commonStep) {
        return ResponseEntity.ok(service.insert(commonStep));
    }

    @ApiOperation("修改工序信息")
    @PreAuthorize("@ss.hasPermi('base:commonStep:edit')")
    @Log(title = "工序信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody CommonStep commonStep) {
        return ResponseEntity.ok(service.update(commonStep));
    }

    @ApiOperation("删除工序信息")
    @PreAuthorize("@ss.hasPermi('base:commonStep:remove')")
    @Log(title = "工序信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stepIds}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] stepIds) {
        return ResponseEntity.ok(service.deleteByStepIds(stepIds));
    }
}
