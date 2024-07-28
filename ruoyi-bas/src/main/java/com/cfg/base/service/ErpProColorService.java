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
import com.cfg.base.mapper.ErpProColorMapper;
import com.cfg.base.domain.ErpProColor;
import com.cfg.base.pojo.query.ErpProColorQuery;

/**
 * 产品颜色管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProColorService {
    @Autowired
    private ErpProColorMapper erpProColorMapper;

    /**
     * 查询产品颜色管理
     *
     * @param id 产品颜色管理主键
     * @return 产品颜色管理
     */
    public ErpProColor selectById(Long id) {
        return erpProColorMapper.selectById(id);
    }

    /**
     * 查询产品颜色管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 产品颜色管理
     */
    public List<ErpProColor> selectList(ErpProColorQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProColor> qw = new QueryWrapper<>();
       Long empId =SecurityUtils.getEmpId();
       if (empId != null) {
           qw.eq("emp_id",empId);
       }
        qw.eq("del_flag",0);
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        String colorCode = query.getColorCode();
        if (!StringUtils.isEmpty(colorCode)) {
            qw.eq("color_code", colorCode);
        }
        String colorNameLike = query.getColorNameLike();
        if (!StringUtils.isEmpty(colorNameLike)) {
            qw.like("color_name", colorNameLike);
        }
        return erpProColorMapper.selectList(qw);
    }

    /**
     * 新增产品颜色管理
     *
     * @param erpProColor 产品颜色管理
     * @return 结果
     */
    public int insert(ErpProColor erpProColor) {
        erpProColor.setDelFlag(0);
        erpProColor.setCreateTime(LocalDateTime.now());
        return erpProColorMapper.insert(erpProColor);
    }

    /**
     * 修改产品颜色管理
     *
     * @param erpProColor 产品颜色管理
     * @return 结果
     */
    public int update(ErpProColor erpProColor) {
        return erpProColorMapper.updateById(erpProColor);
    }

    /**
     * 批量删除产品颜色管理
     *
     * @param ids 需要删除的产品颜色管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProColorMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除产品颜色管理信息
     *
     * @param id 产品颜色管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProColorMapper.updateDelFlagByIds(ids);
    }
}
