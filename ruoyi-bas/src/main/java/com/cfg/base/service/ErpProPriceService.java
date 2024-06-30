package com.cfg.base.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProPriceMapper;
import com.cfg.base.domain.ErpProPrice;
import com.cfg.base.pojo.query.ErpProPriceQuery;

/**
 * 服装工价信息Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProPriceService {
    @Autowired
    private ErpProPriceMapper erpProPriceMapper;

    /**
     * 查询服装工价信息
     *
     * @param id 服装工价信息主键
     * @return 服装工价信息
     */
    public ErpProPrice selectById(Long id) {
        return erpProPriceMapper.selectById(id);
    }

    /**
     * 查询服装工价信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装工价信息
     */
    public List<ErpProPrice> selectList(ErpProPriceQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProPrice> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proid = query.getProid();
        if (proid != null) {
            qw.eq("proId", proid);
        }
        Long stepid = query.getStepid();
        if (stepid != null) {
            qw.eq("stepId", stepid);
        }
        Long sizeid = query.getSizeid();
        if (sizeid != null) {
            qw.eq("sizeId", sizeid);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        BigDecimal price = query.getPrice();
        if (price != null) {
            qw.eq("price", price);
        }
        Long creater = query.getCreater();
        if (creater != null) {
            qw.eq("creater", creater);
        }
        Long updater = query.getUpdater();
        if (updater != null) {
            qw.eq("updater", updater);
        }
        Integer dr = query.getDr();
        if (dr != null) {
            qw.eq("dr", dr);
        }
        return erpProPriceMapper.selectList(qw);
    }

    /**
     * 新增服装工价信息
     *
     * @param erpProPrice 服装工价信息
     * @return 结果
     */
    public int insert(ErpProPrice erpProPrice) {
        erpProPrice.setDelFlag(0);
        erpProPrice.setCreateTime(LocalDateTime.now());
        return erpProPriceMapper.insert(erpProPrice);
    }

    /**
     * 修改服装工价信息
     *
     * @param erpProPrice 服装工价信息
     * @return 结果
     */
    public int update(ErpProPrice erpProPrice) {
        return erpProPriceMapper.updateById(erpProPrice);
    }

    /**
     * 批量删除服装工价信息
     *
     * @param ids 需要删除的服装工价信息主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProPriceMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装工价信息信息
     *
     * @param id 服装工价信息主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProPriceMapper.updateDelFlagByIds(ids);
    }
}
