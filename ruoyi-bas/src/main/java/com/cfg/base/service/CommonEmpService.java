package com.cfg.base.service;

import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.CommonEmpMapper;
import com.ruoyi.common.core.domain.entity.CommonEmp;
import com.cfg.base.pojo.query.CommonEmpQuery;

/**
 * 单位信息Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class CommonEmpService {
    @Autowired
    private CommonEmpMapper commonEmpMapper;

    /**
     * 查询单位信息
     *
     * @param empId 单位信息主键
     * @return 单位信息
     */
    public CommonEmp selectByEmpId(Long empId) {
        return commonEmpMapper.selectById(empId);
    }

    /**
     * 查询单位信息列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 单位信息
     */
    public List<CommonEmp> selectList(CommonEmpQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<CommonEmp> qw = new QueryWrapper<>();
        qw.eq("del_flag","0");
        String empId = query.getEmpId();
        if (!StringUtils.isEmpty(empId)) {
            qw.eq("emp_id", empId);
        }
        String empCode = query.getEmpCode();
        if (!StringUtils.isEmpty(empCode)) {
            qw.eq("emp_code", empCode);
        }
        String empNameLike = query.getEmpNameLike();
        if (!StringUtils.isEmpty(empNameLike)) {
            qw.like("emp_name", empNameLike);
        }
        Long parentId = query.getParentId();
        if (parentId != null) {
            qw.eq("parent_id", parentId);
        }
        String ancestors = query.getAncestors();
        if (!StringUtils.isEmpty(ancestors)) {
            qw.eq("ancestors", ancestors);
        }
        Long orderNum = query.getOrderNum();
        if (orderNum != null) {
            qw.eq("order_num", orderNum);
        }
        return commonEmpMapper.selectList(qw);
    }

    /**
     * 新增单位信息
     *
     * @param commonEmp 单位信息
     * @return 结果
     */
    public int insert(CommonEmp commonEmp) {
        commonEmp.setDelFlag("0");
        commonEmp.setCreateTime(LocalDateTime.now());
        return commonEmpMapper.insert(commonEmp);
    }

    /**
     * 修改单位信息
     *
     * @param commonEmp 单位信息
     * @return 结果
     */
    public int update(CommonEmp commonEmp) {
        return commonEmpMapper.updateById(commonEmp);
    }

    /**
     * 批量删除单位信息
     *
     * @param empIds 需要删除的单位信息主键
     * @return 结果
     */
    public int deleteByEmpIds(Long[] empIds) {
        return commonEmpMapper.updateDelFlagByIds(empIds);
    }

    /**
     * 删除单位信息信息
     *
     * @param empId 单位信息主键
     * @return 结果
     */
    public int deleteByEmpId(Long empId) {
        Long[] empIds = {empId};
        return commonEmpMapper.updateDelFlagByIds(empIds);
    }
}
