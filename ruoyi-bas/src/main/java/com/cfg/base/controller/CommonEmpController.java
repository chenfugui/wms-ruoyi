package com.cfg.base.controller;

import java.util.List;

import com.cfg.base.dto.EmpDTO;
import com.cfg.base.dto.EmpUserDTO;
import com.cfg.idgen.util.ConvertUtils;
import com.cfg.idgen.util.TreeNodeUtils;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.cfg.base.convert.CommonEmpConvert;
import com.ruoyi.common.core.domain.entity.CommonEmp;
import com.cfg.base.pojo.query.CommonEmpQuery;
import com.cfg.base.service.CommonEmpService;
import com.cfg.base.pojo.vo.CommonEmpVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 单位信息Controller
 *
 * @author chenfg
 * @date 2024-04-26
 */
@Api(description ="单位信息接口列表")
@RestController
@RequestMapping("/base/commonEmp")
public class CommonEmpController extends BaseController {
    @Autowired
    private CommonEmpService service;
    @Autowired
    private CommonEmpConvert convert;

    @ApiOperation("查询单位信息列表")
    @PreAuthorize("@ss.hasPermi('base:commonEmp:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<CommonEmp>> list(@RequestBody CommonEmpQuery query, Pageable page) {
        List<CommonEmp> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect()
    {
        CommonEmpQuery empQuery = new CommonEmpQuery();
        List<CommonEmp> emps = service.selectList(empQuery,null);
       List<EmpDTO> empDtos = ConvertUtils.convert(emps, EmpDTO.class);
        return AjaxResult.success(TreeNodeUtils.buildTree(empDtos,"parentId","empId"));
    }

    @ApiOperation("导出单位信息列表")
    @PreAuthorize("@ss.hasPermi('base:commonEmp:export')")
    @Log(title = "单位信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(CommonEmpQuery query) {
        List<CommonEmp> list = service.selectList(query, null);
        ExcelUtil<CommonEmpVO> util = new ExcelUtil<>(CommonEmpVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "单位信息数据"));
    }

    @ApiOperation("获取单位信息详细信息")
    @PreAuthorize("@ss.hasPermi('base:commonEmp:query')")
    @GetMapping(value = "/{empId}")
    public ResponseEntity<CommonEmp> getInfo(@PathVariable("empId") Long empId) {
        return ResponseEntity.ok(service.selectByEmpId(empId));
    }

    @ApiOperation("新增单位信息")
    @PreAuthorize("@ss.hasPermi('base:commonEmp:add')")
    @Log(title = "单位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody CommonEmp commonEmp) {
        return ResponseEntity.ok(service.insert(commonEmp));
    }

    @ApiOperation("修改单位信息")
    @PreAuthorize("@ss.hasPermi('base:commonEmp:edit')")
    @Log(title = "单位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody CommonEmp commonEmp) {
        return ResponseEntity.ok(service.update(commonEmp));
    }

    @ApiOperation("删除单位信息")
    @PreAuthorize("@ss.hasPermi('base:commonEmp:remove')")
    @Log(title = "单位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{empIds}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] empIds) {
        return ResponseEntity.ok(service.deleteByEmpIds(empIds));
    }

    @ApiOperation("用户注册")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> regUser(@RequestBody @Validated EmpUserDTO empUserDTO) {
        return ResponseEntity.ok(service.insertRegUser(empUserDTO));
    }

}
