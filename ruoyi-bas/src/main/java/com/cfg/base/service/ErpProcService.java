package com.cfg.base.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.service.IdGenService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProcMapper;
import com.cfg.base.domain.ErpProc;
import com.cfg.base.pojo.query.ErpProcQuery;

/**
 * 服装工序管理Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProcService {
    @Autowired
    private ErpProcMapper erpProcMapper;

    @Autowired
    private IdGenService idGenService;

    /**
     * 查询服装工序管理
     *
     * @param id 服装工序管理主键
     * @return 服装工序管理
     */
    public ErpProc selectById(Long id) {
        return erpProcMapper.selectById(id);
    }

    /**
     * 查询服装工序管理列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装工序管理
     */
    public List<ErpProc> selectList(ErpProcQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProc> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String stepCode = query.getStepCode();
        if (!StringUtils.isEmpty(stepCode)) {
            qw.eq("step_code", stepCode);
        }
        String stepNameLike = query.getStepNameLike();
        if (!StringUtils.isEmpty(stepNameLike)) {
            qw.like("step_name", stepNameLike);
        }
        BigDecimal stepPrice = query.getStepPrice();
        if (stepPrice != null) {
            qw.eq("step_price", stepPrice);
        }
        String empCode = query.getEmpCode();
        if (!StringUtils.isEmpty(empCode)) {
            qw.eq("emp_code", empCode);
        }
        Long seqNo = query.getSeqNo();
        if (seqNo != null) {
            qw.eq("seq_no", seqNo);
        }
        String status = query.getStatus();
        if (!StringUtils.isEmpty(status)) {
            qw.eq("status", status);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        return erpProcMapper.selectList(qw);
    }

    /**
     * 新增服装工序管理
     *
     * @param erpProc 服装工序管理
     * @return 结果
     */
    public int insert(ErpProc erpProc) {
        erpProc.setDelFlag(0);
        erpProc.setCreateTime(LocalDateTime.now());
        erpProc.setId(idGenService.getSeqId("proc_id"));
        return erpProcMapper.insert(erpProc);
    }

    /**
     * 修改服装工序管理
     *
     * @param erpProc 服装工序管理
     * @return 结果
     */
    public int update(ErpProc erpProc) {
        return erpProcMapper.updateById(erpProc);
    }

    /**
     * 批量删除服装工序管理
     *
     * @param ids 需要删除的服装工序管理主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProcMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装工序管理信息
     *
     * @param id 服装工序管理主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProcMapper.updateDelFlagByIds(ids);
    }
}
