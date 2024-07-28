package com.cfg.base.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.idgen.service.IdGenService;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProBatchExeMapper;
import com.cfg.base.domain.ErpProBatchExe;
import com.cfg.base.pojo.query.ErpProBatchExeQuery;

/**
 * 服装生产进度Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpProBatchExeService {
    @Autowired
    private ErpProBatchExeMapper erpProBatchExeMapper;

    @Autowired
    private IdGenService idGenService;

    /**
     * 查询服装生产进度
     *
     * @param id 服装生产进度主键
     * @return 服装生产进度
     */
    public ErpProBatchExe selectById(Long id) {
        return erpProBatchExeMapper.selectById(id);
    }

    /**
     * 查询服装生产进度列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 服装生产进度
     */
    public List<ErpProBatchExe> selectList(ErpProBatchExeQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpProBatchExe> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        Long batchId = query.getBatchId();
        if (batchId != null) {
            qw.eq("batch_id", batchId);
        }
        Long stepId = query.getStepId();
        if (stepId != null) {
            qw.eq("step_id", stepId);
        }
        String makeStatus = query.getMakeStatus();
        if (!StringUtils.isEmpty(makeStatus)) {
            qw.eq("make_status", makeStatus);
        }
        Long realMakeNum = query.getRealMakeNum();
        if (realMakeNum != null) {
            qw.eq("real_make_num", realMakeNum);
        }
        Long empId = query.getEmpId();
        if (empId != null) {
            qw.eq("emp_id", empId);
        }
        Long scanBy = query.getScanBy();
        if (scanBy != null) {
            qw.eq("scan_by", scanBy);
        }
        LocalDateTime scanTime = query.getScanTime();
        if (scanTime != null) {
            qw.eq("scan_time", scanTime);
        }
        BigDecimal salary = query.getSalary();
        if (salary != null) {
            qw.eq("salary", salary);
        }
        Integer delFlag = query.getDelFlag();
        if (delFlag != null) {
            qw.eq("del_flag", delFlag);
        }
        return erpProBatchExeMapper.selectList(qw);
    }

    /**
     * 新增服装生产进度
     *
     * @param erpProBatchExe 服装生产进度
     * @return 结果
     */
    public int insert(ErpProBatchExe erpProBatchExe) {
        erpProBatchExe.setDelFlag(0);
        erpProBatchExe.setCreateTime(LocalDateTime.now());
        erpProBatchExe.setId(idGenService.getSeqId("exe_id"));
        erpProBatchExe.setEmpId(SecurityUtils.getEmpId());
        return erpProBatchExeMapper.insert(erpProBatchExe);
    }

    /**
     * 修改服装生产进度
     *
     * @param erpProBatchExe 服装生产进度
     * @return 结果
     */
    public int update(ErpProBatchExe erpProBatchExe) {
        return erpProBatchExeMapper.updateById(erpProBatchExe);
    }

    /**
     * 批量删除服装生产进度
     *
     * @param ids 需要删除的服装生产进度主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpProBatchExeMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除服装生产进度信息
     *
     * @param id 服装生产进度主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpProBatchExeMapper.updateDelFlagByIds(ids);
    }
}
