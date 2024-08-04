package com.cfg.base.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cfg.base.dto.AppMenuGroupDTO;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.compress.utils.Lists;
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
import com.cfg.base.convert.ErpAppMenuConvert;
import com.cfg.base.domain.ErpAppMenu;
import com.cfg.base.pojo.query.ErpAppMenuQuery;
import com.cfg.base.service.ErpAppMenuService;
import com.cfg.base.pojo.vo.ErpAppMenuVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * app功能菜单Controller
 *
 * @author chenfg
 * @date 2024-08-03
 */
@Api(description ="app功能菜单接口列表")
@RestController
@RequestMapping("/base/erpAppMenu")
public class ErpAppMenuController extends BaseController {
    @Autowired
    private ErpAppMenuService service;
    @Autowired
    private ErpAppMenuConvert convert;

    @ApiOperation("查询app功能菜单列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpAppMenu>> list(@RequestBody ErpAppMenuQuery query, Pageable page) {
        List<ErpAppMenu> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出app功能菜单列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:export')")
    @Log(title = "app功能菜单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpAppMenuQuery query) {
        List<ErpAppMenu> list = service.selectList(query, null);
        ExcelUtil<ErpAppMenuVO> util = new ExcelUtil<>(ErpAppMenuVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "app功能菜单数据"));
    }

    @ApiOperation("获取app功能菜单详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:query')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ErpAppMenu> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.selectById(id));
    }

    @ApiOperation("新增app功能菜单")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:add')")
    @Log(title = "app功能菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpAppMenu erpAppMenu) {
        return ResponseEntity.ok(service.insert(erpAppMenu));
    }

    @ApiOperation("修改app功能菜单")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:edit')")
    @Log(title = "app功能菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpAppMenu erpAppMenu) {
        return ResponseEntity.ok(service.update(erpAppMenu));
    }

    @ApiOperation("删除app功能菜单")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:remove')")
    @Log(title = "app功能菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] ids) {
        return ResponseEntity.ok(service.deleteByIds(ids));
    }

    @ApiOperation("查询app功能菜单列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:list')")
    @GetMapping("/listByRole")
    public ResponseEntity<List<ErpAppMenu>> list(@RequestParam("roleId") Long roleId) {
        List<ErpAppMenu> list = service.selectListByRoleId(roleId);
        return ResponseEntity.ok(list);
    }


    @ApiOperation("查询app功能菜单列表")
    @PreAuthorize("@ss.hasPermi('base:erpAppMenu:list')")
    @GetMapping("/listAppMenu")
    public ResponseEntity<AppMenuGroupDTO> list() {
        Long[] roleIdArr = SecurityUtils.getLoginSysUser().getRoleIds();
        AppMenuGroupDTO menuGroupDTO=new AppMenuGroupDTO();
        if (roleIdArr != null && roleIdArr.length > 0) {
            List<Long> roleIdList = Arrays.asList(roleIdArr);
             menuGroupDTO = service.selectListByRoleIdList(roleIdList);
        }
        return ResponseEntity.ok(menuGroupDTO);
    }
}
