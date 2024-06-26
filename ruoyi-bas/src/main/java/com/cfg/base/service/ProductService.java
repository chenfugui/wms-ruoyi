package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ProductMapper;
import com.cfg.base.domain.Product;
import com.cfg.base.pojo.query.ProductQuery;

/**
 * 产品信息表Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    /**
     * 查询产品信息表
     *
     * @param proId 产品信息表主键
     * @return 产品信息表
     */
    public Product selectByProId(Long proId) {
        return productMapper.selectById(proId);
    }

    /**
     * 查询产品信息表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 产品信息表
     */
    public List<Product> selectList(ProductQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<Product> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String proCode = query.getProCode();
        if (!StringUtils.isEmpty(proCode)) {
            qw.eq("pro_code", proCode);
        }
        String proNameLike = query.getProNameLike();
        if (!StringUtils.isEmpty(proNameLike)) {
            qw.like("pro_name", proNameLike);
        }
        String proDesc = query.getProDesc();
        if (!StringUtils.isEmpty(proDesc)) {
            qw.eq("pro_desc", proDesc);
        }
        String proType = query.getProType();
        if (!StringUtils.isEmpty(proType)) {
            qw.eq("pro_type", proType);
        }
        String proMemo = query.getProMemo();
        if (!StringUtils.isEmpty(proMemo)) {
            qw.eq("pro_memo", proMemo);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        return productMapper.selectList(qw);
    }

    /**
     * 新增产品信息表
     *
     * @param product 产品信息表
     * @return 结果
     */
    public int insert(Product product) {
        product.setDelFlag("0");
        product.setCreateTime(LocalDateTime.now());
        return productMapper.insert(product);
    }

    /**
     * 修改产品信息表
     *
     * @param product 产品信息表
     * @return 结果
     */
    public int update(Product product) {
        product.setEmpId(null);
        return productMapper.updateById(product);
    }

    /**
     * 批量删除产品信息表
     *
     * @param proIds 需要删除的产品信息表主键
     * @return 结果
     */
    public int deleteByProIds(Long[] proIds) {
        return productMapper.updateDelFlagByIds(proIds);
    }

    /**
     * 删除产品信息表信息
     *
     * @param proId 产品信息表主键
     * @return 结果
     */
    public int deleteByProId(Long proId) {
        Long[] proIds = {proId};
        return productMapper.updateDelFlagByIds(proIds);
    }
}
