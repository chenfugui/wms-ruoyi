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
import com.cfg.base.convert.ErpAppVerConvert;
import com.cfg.base.domain.ErpAppVer;
import com.cfg.base.pojo.query.ErpAppVerQuery;
import com.cfg.base.service.ErpAppVerService;
import com.cfg.base.pojo.vo.ErpAppVerVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * App版本Controller
 *
 * @author chenfg
 * @date 2025-02-22
 */
@Api(description ="App版本接口列表")
@RestController
@RequestMapping("/base/erpAppVer")
public class ErpAppVerController extends BaseController {
    @Autowired
    private ErpAppVerService service;
    @Autowired
    private ErpAppVerConvert convert;

    @ApiOperation("查询App版本列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpAppVer>> list(@RequestBody ErpAppVerQuery query, Pageable page) {
        List<ErpAppVer> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出App版本列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:export')")
    @Log(title = "App版本", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpAppVerQuery query) {
        List<ErpAppVer> list = service.selectList(query, null);
        ExcelUtil<ErpAppVerVO> util = new ExcelUtil<>(ErpAppVerVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "App版本数据"));
    }

    @ApiOperation("获取App版本详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpAppVer> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增App版本")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:add')")
    @Log(title = "App版本", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpAppVer erpAppVer) {
        return ResponseEntity.ok(service.insert(erpAppVer));
    }

    @ApiOperation("修改App版本")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:edit')")
    @Log(title = "App版本", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpAppVer erpAppVer) {
        return ResponseEntity.ok(service.update(erpAppVer));
    }

    @ApiOperation("删除App版本")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:remove')")
    @Log(title = "App版本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }

    @ApiOperation("查询最新App版本")
    @PreAuthorize("@ss.hasPermi('base:erpAppVer:list')")
    @GetMapping("/newest")
    public ResponseEntity<ErpAppVer> newestAppVersion() {
        ErpAppVer erpAppVer  = service.selectByMaxReleaseNum();
        return ResponseEntity.ok(erpAppVer);
    }
}
