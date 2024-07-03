package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpSizeMapper;
import com.cfg.base.domain.ErpSize;
import com.cfg.base.pojo.query.ErpSizeQuery;

/**
 * 服装尺码管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpSizeService {
    @Autowired
    private ErpSizeMapper erpSizeMapper;

    /**
     * 查询服装尺码管理
     *
     * @param id 服装尺码管理主键
     * @return 服装尺码管理
     */
    public ErpSize selectById(Long id) {
        return erpSizeMapper.selectById(id);
    }

    /**
     * 查询服装尺码管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装尺码管理
     */
    public List<ErpSize> selectList(ErpSizeQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpSize> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String colorCode = query.getColorCode();
        if (!StringUtils.isEmpty(colorCode)) {
            qw.eq("color_code", colorCode);
        }
        String colorNameLike = query.getColorNameLike();
        if (!StringUtils.isEmpty(colorNameLike)) {
            qw.like("color_name", colorNameLike);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        Integer dr = query.getDr();
        if (dr != null) {
            qw.eq("dr", dr);
        }
        return erpSizeMapper.selectList(qw);
    }

    /**
     * 新增服装尺码管理
     *
     * @param erpSize 服装尺码管理
     * @return 结果
     */
    public int insert(ErpSize erpSize) {
        erpSize.setDelFlag(0);
        erpSize.setCreateTime(LocalDateTime.now());
        return erpSizeMapper.insert(erpSize);
    }

    /**
     * 修改服装尺码管理
     *
     * @param erpSize 服装尺码管理
     * @return 结果
     */
    public int update(ErpSize erpSize) {
        return erpSizeMapper.updateById(erpSize);
    }

    /**
     * 批量删除服装尺码管理
     *
     * @param ids 需要删除的服装尺码管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpSizeMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装尺码管理信息
     *
     * @param id 服装尺码管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpSizeMapper.updateDelFlagByIds(ids);
    }
}
