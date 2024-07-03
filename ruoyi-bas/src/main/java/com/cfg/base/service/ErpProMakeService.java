package com.cfg.base.service;

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
import com.cfg.base.mapper.ErpProMakeMapper;
import com.cfg.base.domain.ErpProMake;
import com.cfg.base.pojo.query.ErpProMakeQuery;

/**
 * 服装生产管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProMakeService {
    @Autowired
    private ErpProMakeMapper erpProMakeMapper;

    /**
     * 查询服装生产管理
     *
     * @param id 服装生产管理主键
     * @return 服装生产管理
     */
    public ErpProMake selectById(Long id) {
        return erpProMakeMapper.selectById(id);
    }

    /**
     * 查询服装生产管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装生产管理
     */
    public List<ErpProMake> selectList(ErpProMakeQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProMake> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long proId = query.getProId();
        if (proId != null) {
            qw.eq("pro_id", proId);
        }
        String proMakeNo = query.getProMakeNo();
        if (!StringUtils.isEmpty(proMakeNo)) {
            qw.eq("pro_make_no", proMakeNo);
        }
        LocalDateTime makeStartTime = query.getMakeStartTime();
        if (makeStartTime != null) {
            qw.eq("make_start_time", makeStartTime);
        }
        LocalDateTime makeEndTime = query.getMakeEndTime();
        if (makeEndTime != null) {
            qw.eq("make_end_time", makeEndTime);
        }
        LocalDateTime deliverTime = query.getDeliverTime();
        if (deliverTime != null) {
            qw.eq("deliver_time", deliverTime);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
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
        return erpProMakeMapper.selectList(qw);
    }

    /**
     * 新增服装生产管理
     *
     * @param erpProMake 服装生产管理
     * @return 结果
     */
    public int insert(ErpProMake erpProMake) {
        erpProMake.setDelFlag(0);
        erpProMake.setCreateTime(LocalDateTime.now());
        return erpProMakeMapper.insert(erpProMake);
    }

    /**
     * 修改服装生产管理
     *
     * @param erpProMake 服装生产管理
     * @return 结果
     */
    public int update(ErpProMake erpProMake) {
        return erpProMakeMapper.updateById(erpProMake);
    }

    /**
     * 批量删除服装生产管理
     *
     * @param ids 需要删除的服装生产管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProMakeMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装生产管理信息
     *
     * @param id 服装生产管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProMakeMapper.updateDelFlagByIds(ids);
    }
}
