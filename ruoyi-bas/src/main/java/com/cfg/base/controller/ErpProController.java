package com.cfg.base.controller;

import java.util.List;

import com.cfg.base.pojo.dto.ErpProDTO;
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
import com.cfg.base.convert.ErpProConvert;
import com.cfg.base.domain.ErpPro;
import com.cfg.base.pojo.query.ErpProQuery;
import com.cfg.base.service.ErpProService;
import com.cfg.base.pojo.vo.ErpProVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 服装产品管理Controller
 *
 * @author chenfg
 * @date 2024-07-02
 */
@Api(description ="服装产品管理接口列表")
@RestController
@RequestMapping("/base/erpPro")
public class ErpProController extends BaseController {
    @Autowired
    private ErpProService service;
    @Autowired
    private ErpProConvert convert;

    @ApiOperation("查询服装产品管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpPro:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<ErpPro>> list(@RequestBody ErpProQuery query, Pageable page) {
        query.setEmpid(SecurityUtils.getEmpId());
        List<ErpPro> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出服装产品管理列表")
    @PreAuthorize("@ss.hasPermi('base:erpPro:export')")
    @Log(title = "服装产品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ErpProQuery query) {
        query.setEmpid(SecurityUtils.getEmpId());
        List<ErpPro> list = service.selectList(query, null);
        ExcelUtil<ErpProVO> util = new ExcelUtil<>(ErpProVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "服装产品管理数据"));
    }

    @ApiOperation("获取服装产品管理详细信息")
    @PreAuthorize("@ss.hasPermi('base:erpPro:query')")
    @GetMapping(value = "/{proId}")
    public ResponseEntity<ErpProDTO> getInfo(@PathVariable("proId") Long proId) {
        return ResponseEntity.ok(service.selectByProId(proId));
    }

    @ApiOperation("新增服装产品管理")
    @PreAuthorize("@ss.hasPermi('base:erpPro:add')")
    @Log(title = "服装产品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ErpPro erpPro) {
        return ResponseEntity.ok(service.insert(erpPro));
    }

    @ApiOperation("修改服装产品管理")
    @PreAuthorize("@ss.hasPermi('base:erpPro:edit')")
    @Log(title = "服装产品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody ErpPro erpPro) {
        return ResponseEntity.ok(service.update(erpPro));
    }

    @ApiOperation("删除服装产品管理")
    @PreAuthorize("@ss.hasPermi('base:erpPro:remove')")
    @Log(title = "服装产品管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proIds}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] proIds) {
        return ResponseEntity.ok(service.deleteByProIds(proIds));
    }

    @ApiOperation("新增服装产品管理")
    @PreAuthorize("@ss.hasPermi('base:erpPro:add')")
    @Log(title = "服装产品管理", businessType = BusinessType.INSERT)
    @PostMapping("/insertall")
    public ResponseEntity<Integer> addProInfo(@RequestBody ErpProDTO erpProDTO) {
        return ResponseEntity.ok(service.insertAll(erpProDTO));
    }


    @ApiOperation("新增产品工价管理")
    @PreAuthorize("@ss.hasPermi('base:erpPro:add')")
    @Log(title = "产品工价设置", businessType = BusinessType.INSERT)
    @PostMapping("/savePrice")
    public WrapperResponse<Integer> saveProPrice(@RequestBody ErpProDTO erpProDTO) {
        service.saveProPrice(erpProDTO);
        return WrapperResponse.success(erpProDTO.getPriceList().size());
    }

}
