package com.cfg.base.controller;

import java.util.List;

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
import com.cfg.base.convert.ProductConvert;
import com.cfg.base.domain.Product;
import com.cfg.base.pojo.query.ProductQuery;
import com.cfg.base.service.ProductService;
import com.cfg.base.pojo.vo.ProductVO;
import com.ruoyi.common.utils.poi.ExcelUtil;
/**
 * 产品信息表Controller
 *
 * @author chenfg
 * @date 2024-04-30
 */
@Api(description ="产品信息表接口列表")
@RestController
@RequestMapping("/base/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductConvert convert;

    @ApiOperation("查询产品信息表列表")
    @PreAuthorize("@ss.hasPermi('base:product:list')")
    @PostMapping("/list")
    public ResponseEntity<Page<Product>> list(@RequestBody ProductQuery query, Pageable page) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<Product> list = service.selectList(query, page);
        return ResponseEntity.ok(new PageImpl<>(list, page, ((com.github.pagehelper.Page)list).getTotal()));
    }

    @ApiOperation("导出产品信息表列表")
    @PreAuthorize("@ss.hasPermi('base:product:export')")
    @Log(title = "产品信息表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public ResponseEntity<String> export(ProductQuery query) {
        query.setEmpId(SecurityUtils.getEmpId());
        List<Product> list = service.selectList(query, null);
        ExcelUtil<ProductVO> util = new ExcelUtil<>(ProductVO.class);
        return ResponseEntity.ok(util.writeExcel(convert.dos2vos(list), "产品信息表数据"));
    }

    @ApiOperation("获取产品信息表详细信息")
    @PreAuthorize("@ss.hasPermi('base:product:query')")
    @GetMapping(value = "/{proId}")
    public ResponseEntity<Product> getInfo(@PathVariable("proId") Long proId) {
        return ResponseEntity.ok(service.selectByProId(proId));
    }

    @ApiOperation("新增产品信息表")
    @PreAuthorize("@ss.hasPermi('base:product:add')")
    @Log(title = "产品信息表", businessType = BusinessType.INSERT)
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Product product) {
        product.setEmpId(SecurityUtils.getEmpId());
        return ResponseEntity.ok(service.insert(product));
    }

    @ApiOperation("修改产品信息表")
    @PreAuthorize("@ss.hasPermi('base:product:edit')")
    @Log(title = "产品信息表", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResponseEntity<Integer> edit(@RequestBody Product product) {
        return ResponseEntity.ok(service.update(product));
    }

    @ApiOperation("删除产品信息表")
    @PreAuthorize("@ss.hasPermi('base:product:remove')")
    @Log(title = "产品信息表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proIds}")
    public ResponseEntity<Integer> remove(@PathVariable Long[] proIds) {
        return ResponseEntity.ok(service.deleteByProIds(proIds));
    }
}
