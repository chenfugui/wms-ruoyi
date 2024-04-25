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
import com.cfg.base.convert.CommonWkbillConvert;
import com.cfg.base.domain.CommonWkbill;
import com.cfg.base.pojo.query.CommonWkbillQuery;
import com.cfg.base.service.CommonWkbillService;
import com.cfg.base.pojo.vo.CommonWkbillVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 工单信息Controller
 *
 * @author chenfg
 * @date 2024-04-25
 */
@Api(description ="工单信息接口列表")
@RestController
@RequestMapping("/base/commonWkbill")
public class CommonWkbillController extends BaseController {
    @Autowired
    private CommonWkbillService service;
    @Autowired
    private CommonWkbillConvert convert;

    @ApiOperation("查询工单信息列表")
    @PreAuthorize("@ss.hasPermi('base:commonWkbill:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<CommonWkbill>> list(@RequestBody CommonWkbillQuery query, Pageable page) {
        List<CommonWkbill> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出工单信息列表")
    @PreAuthorize("@ss.hasPermi('base:commonWkbill:export')")
    @Log(title = "工单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(CommonWkbillQuery query) {
        List<CommonWkbill> list = service.selectList(query, null);
        ExcelUtil<CommonWkbillVO> util = new ExcelUtil<>(CommonWkbillVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "工单信息数据"));
    }

    @ApiOperation("获取工单信息详细信息")
    @PreAuthorize("@ss.hasPermi('base:commonWkbill:query')")
    @GetMapping(value = "/{wkbillId}")
    public ResponseEntity<CommonWkbill> getInfo(@PathVariable("wkbillId") Long wkbillId) {
        return ResponseEntity.ok(service.selectByWkbillId(wkbillId));
    }

    @ApiOperation("新增工单信息")
    @PreAuthorize("@ss.hasPermi('base:commonWkbill:add')")
    @Log(title = "工单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody CommonWkbill commonWkbill) {
        return ResponseEntity.ok(service.insert(commonWkbill));
    }

    @ApiOperation("修改工单信息")
    @PreAuthorize("@ss.hasPermi('base:commonWkbill:edit')")
    @Log(title = "工单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody CommonWkbill commonWkbill) {
        return ResponseEntity.ok(service.update(commonWkbill));
    }

    @ApiOperation("删除工单信息")
    @PreAuthorize("@ss.hasPermi('base:commonWkbill:remove')")
    @Log(title = "工单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wkbillIds}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] wkbillIds) {
        return ResponseEntity.ok(service.deleteByWkbillIds(wkbillIds));
    }
}
