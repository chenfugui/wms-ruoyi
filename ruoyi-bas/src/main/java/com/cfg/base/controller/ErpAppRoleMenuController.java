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
import com.cfg.base.convert.ErpAppRoleMenuConvert;
import com.cfg.base.domain.ErpAppRoleMenu;
import com.cfg.base.pojo.query.ErpAppRoleMenuQuery;
import com.cfg.base.service.ErpAppRoleMenuService;
import com.cfg.base.pojo.vo.ErpAppRoleMenuVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 角色菜单表Controller
 *
 * @author chenfg
 * @date 2024-08-03
 */
@Api(description ="角色菜单表接口列表")
@RestController
@RequestMapping("/base/erpAppRoleMenu")
public class ErpAppRoleMenuController extends BaseController {
    @Autowired
    private ErpAppRoleMenuService service;
    @Autowired
    private ErpAppRoleMenuConvert convert;

    @ApiOperation("查询角色菜单表列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppRoleMenu:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpAppRoleMenu>> list(@RequestBody ErpAppRoleMenuQuery query, Pageable page) {
        List<ErpAppRoleMenu> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出角色菜单表列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppRoleMenu:export')")
    @Log(title = "角色菜单表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpAppRoleMenuQuery query) {
        List<ErpAppRoleMenu> list = service.selectList(query, null);
        ExcelUtil<ErpAppRoleMenuVO> util = new ExcelUtil<>(ErpAppRoleMenuVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "角色菜单表数据"));
    }

    @ApiOperation("获取角色菜单表详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpAppRoleMenu:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpAppRoleMenu> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增角色菜单表")
    @PreAuthorize("@ss.hasPermi('base:erpAppRoleMenu:add')")
    @Log(title = "角色菜单表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpAppRoleMenu erpAppRoleMenu) {
        return ResponseEntity.ok(service.insert(erpAppRoleMenu));
    }

    @ApiOperation("修改角色菜单表")
    @PreAuthorize("@ss.hasPermi('base:erpAppRoleMenu:edit')")
    @Log(title = "角色菜单表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpAppRoleMenu erpAppRoleMenu) {
        return ResponseEntity.ok(service.update(erpAppRoleMenu));
    }

    @ApiOperation("删除角色菜单表")
    @PreAuthorize("@ss.hasPermi('base:erpAppRoleMenu:remove')")
    @Log(title = "角色菜单表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }
}
