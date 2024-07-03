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
import com.cfg.base.mapper.ErpColorMapper;
import com.cfg.base.domain.ErpColor;
import com.cfg.base.pojo.query.ErpColorQuery;

/**
 * 服装颜色管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpColorService {
    @Autowired
    private ErpColorMapper erpColorMapper;

    /**
     * 查询服装颜色管理
     *
     * @param id 服装颜色管理主键
     * @return 服装颜色管理
     */
    public ErpColor selectById(Long id) {
        return erpColorMapper.selectById(id);
    }

    /**
     * 查询服装颜色管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装颜色管理
     */
    public List<ErpColor> selectList(ErpColorQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpColor> qw = new QueryWrapper<>();
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
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        return erpColorMapper.selectList(qw);
    }

    /**
     * 新增服装颜色管理
     *
     * @param erpColor 服装颜色管理
     * @return 结果
     */
    public int insert(ErpColor erpColor) {
        erpColor.setDelFlag(0);
        erpColor.setCreateTime(LocalDateTime.now());
        return erpColorMapper.insert(erpColor);
    }

    /**
     * 修改服装颜色管理
     *
     * @param erpColor 服装颜色管理
     * @return 结果
     */
    public int update(ErpColor erpColor) {
        return erpColorMapper.updateById(erpColor);
    }

    /**
     * 批量删除服装颜色管理
     *
     * @param ids 需要删除的服装颜色管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpColorMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装颜色管理信息
     *
     * @param id 服装颜色管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpColorMapper.updateDelFlagByIds(ids);
    }
}
