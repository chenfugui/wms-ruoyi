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
import com.cfg.base.convert.ErpEmpDicConvert;
import com.cfg.base.domain.ErpEmpDic;
import com.cfg.base.pojo.query.ErpEmpDicQuery;
import com.cfg.base.service.ErpEmpDicService;
import com.cfg.base.pojo.vo.ErpEmpDicVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 常用字典管理Controller
 *
 * @author chenfg
 * @date 2024-11-09
 */
@Api(description ="常用字典管理接口列表")
@RestController
@RequestMapping("/base/erpEmpDic")
public class ErpEmpDicController extends BaseController {
    @Autowired
    private ErpEmpDicService service;
    @Autowired
    private ErpEmpDicConvert convert;

    @ApiOperation("查询常用字典管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpEmpDic:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpEmpDic>> list(@RequestBody ErpEmpDicQuery query, Pageable page) {
        List<ErpEmpDic> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出常用字典管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpEmpDic:export')")
    @Log(title = "常用字典管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpEmpDicQuery query) {
        List<ErpEmpDic> list = service.selectList(query, null);
        ExcelUtil<ErpEmpDicVO> util = new ExcelUtil<>(ErpEmpDicVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "常用字典管理数据"));
    }

    @ApiOperation("获取常用字典管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpEmpDic:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpEmpDic> getInfo(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增常用字典管理")
    @PreAuthorize("@ss.hasPermi('base:erpEmpDic:add')")
    @Log(title = "常用字典管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpEmpDic erpEmpDic) {
        return ResponseEntity.ok(service.insert(erpEmpDic));
    }

    @ApiOperation("修改常用字典管理")
    @PreAuthorize("@ss.hasPermi('base:erpEmpDic:edit')")
    @Log(title = "常用字典管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpEmpDic erpEmpDic) {
        return ResponseEntity.ok(service.update(erpEmpDic));
    }

    @ApiOperation("删除常用字典管理")
    @PreAuthorize("@ss.hasPermi('base:erpEmpDic:remove')")
    @Log(title = "常用字典管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Integer[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
