package com.cfg.base.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import cn.hutool.Hutool;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.thread.lock.LockUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.pojo.dto.ErpProBatchExeDTO;
import com.cfg.base.pojo.dto.ErpProMakeBatchDTO;
import com.cfg.idgen.enums.SalaryTypeEnum;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.util.CommonUtils;
import com.cfg.idgen.util.OperatorUtils;
import com.cfg.idgen.util.RedisUtilContext;
import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpProBatchExeMapper;
import com.cfg.base.domain.ErpProBatchExe;
import com.cfg.base.pojo.query.ErpProBatchExeQuery;
import org.springframework.transaction.annotation.Transactional;

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
    private ErpProMakeBatchService makeBatchService;

    @Autowired
    private IdGenService idGenService;
    @Autowired
    private RedisUtilContext redisUtilContext;

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
     * @param erpProBatchExe 服装生产进度
     * @return 结果
     */
    public int insert(ErpProBatchExe erpProBatchExe) {
        erpProBatchExe.setDelFlag(0);
        erpProBatchExe.setCreateTime(LocalDateTime.now());
        erpProBatchExe.setId(idGenService.getSeqId("exe_id"));
        erpProBatchExe.setEmpId(SecurityUtils.getEmpId());
        OperatorUtils.setCreateInfo(erpProBatchExe);
        return erpProBatchExeMapper.insert(erpProBatchExe);
    }

    /**
     * 修改服装生产进度
     *
     * @param erpProBatchExe 服装生产进度
     * @return 结果
     */
    public int update(ErpProBatchExe erpProBatchExe) {
        OperatorUtils.setUpdateInfo(erpProBatchExe);
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

    /**
     * 根据makeId查询生产进度
     * @param makeId
     * @return
     */
    public List<ErpProBatchExe> selectByMakeId(Long makeId) {
        return erpProBatchExeMapper.selectByProMakeIds(Collections.singletonList(makeId));
    }

    /**
     * 根据makeIds查询生产进度
     * @param makeIds
     * @return List<ErpProBatchExe>
     */
    public List<ErpProBatchExe> selectByMakeIds(List<Long> makeIds) {
        return erpProBatchExeMapper.selectByProMakeIds(makeIds);
    }

    /***
     * @author chenfg
     * @date: 2024/11/20 17:33
     * @description:  根据生产id删除批次数据
     * @param makeIds 生产id
     * @return: int
     */
    public int deleteByMakeIds(List<Long> makeIds) {
        return 0;
    }

    /**
     * 新增服装生产进度
     * @param batchDTOS 服装生产进度
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertBatch(List<ErpProMakeBatchDTO> batchDTOS) {
        Assert.isTrue(CollectionUtils.isNotEmpty(batchDTOS), "扫菲录入数据为空");
        ErpProMakeBatchDTO proMakeBatchDTO =batchDTOS.get(0);
        String key = "lock:batch:"+proMakeBatchDTO.getProId();
        String reqId = CommonUtils.getUUID();
       boolean lock = redisUtilContext.lock(key, reqId, 20, 20);
       if(lock){
           try {
               List<ErpProMakeBatchDTO> dbMakeBatchDTOList = makeBatchService.selectItemMakeInfoById(batchDTOS.get(0).getId());
               Map<Long,ErpProMakeBatchDTO> stepBatchDTOMap = new HashMap<>();
               for (ErpProMakeBatchDTO erpProMakeBatchDTO : dbMakeBatchDTOList) {
                   stepBatchDTOMap.put(erpProMakeBatchDTO.getStepId(), erpProMakeBatchDTO);
               }
               for (ErpProMakeBatchDTO batchDTO : batchDTOS) {
                   if(batchDTO.getRegNum()==null||batchDTO.getRegNum()<=0) {
                       continue;
                   }
                   Long stepId = batchDTO.getStepId();
                   Long regNum = batchDTO.getRegNum();
                   ErpProMakeBatchDTO dbBatchDTO = stepBatchDTOMap.get(stepId);
                   if(regNum>(dbBatchDTO.getMakeNum() - dbBatchDTO.getCompleteNum()) || regNum<0){
                       throw new RuntimeException("工序完成数量不能大于剩余数量");
                   }
                   ErpProBatchExe erpProBatchExe = new ErpProBatchExe();
                   erpProBatchExe.setDelFlag(0);
                   erpProBatchExe.setCreateTime(LocalDateTime.now());
                   erpProBatchExe.setId(idGenService.getSeqId("exe_id"));
                   erpProBatchExe.setEmpId(SecurityUtils.getEmpId());
                   erpProBatchExe.setBatchId(batchDTO.getId());
                   erpProBatchExe.setStepId(stepId);
                   erpProBatchExe.setRealMakeNum(regNum);
                   erpProBatchExe.setScanBy(SecurityUtils.getUserId());
                   erpProBatchExe.setScanTime(LocalDateTime.now());
                   OperatorUtils.setCreateInfo(erpProBatchExe);
                   erpProBatchExeMapper.insert(erpProBatchExe);
               }
           }finally {
               redisUtilContext.unLock(key, reqId);
           }
       }
       return 1;

    }

    /**
     * @author chenfg
     * @date: 2024/12/11 11:17
     * @description:  查询扫菲记录
     * @param batchExeDTO
     * @return: java.util.List<com.cfg.base.pojo.dto.ErpProBatchExeDTO>
     */
    public List<ErpProBatchExeDTO> selectScanRcdList(ErpProBatchExeDTO batchExeDTO) {
        return erpProBatchExeMapper.selectScanRcdList(batchExeDTO);
    }


    /**
     * @author chenfg
     * @date: 2024/12/11 11:17
     * @description:  工资查询
     * @param batchExeDTO
     * @return: java.util.List<com.cfg.base.pojo.dto.ErpProBatchExeDTO>
     */
    public List<ErpProBatchExeDTO> selectSalaryList(ErpProBatchExeDTO batchExeDTO) {
        if(SalaryTypeEnum.SALARY_MONTH.getCode().equals(batchExeDTO.getSalaryType())){
            return erpProBatchExeMapper.selectSalaryMonth(batchExeDTO);
        }
        return erpProBatchExeMapper.selectSalaryDay(batchExeDTO);
    }
}
