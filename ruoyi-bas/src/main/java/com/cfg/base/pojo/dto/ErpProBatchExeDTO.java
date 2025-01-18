package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产进度 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProBatchExeDTO extends BaseAudit {
    private Long id;
    private Long batchId;
    private Long stepId;
    private String makeStatus;
    private Long realMakeNum;
    private Long empId;
    private Long scanBy;
    private LocalDateTime scanTime;
    private BigDecimal salary;
    private Integer delFlag;
    private Long proId;
    private Long sizeId;
    private Long colorId;
    private String sizeName;
    private String colorName;
    private String stepName;
    private BigDecimal wkSalary;
    private String scanDay;
    private String scanMonth;
    private String scanYear;
    private String userName;
    private String startDay;
    private String endDay;
    // 1 月  2 日
    private String salaryType;

    public static void main(String[] args) {
        ErpProBatchExeDTO batchExeDTO = new ErpProBatchExeDTO();
        batchExeDTO.setSalaryType("1");
        batchExeDTO.setScanDay("2021-01-01");
        batchExeDTO.setScanMonth("2021-01");
        batchExeDTO.setScanYear("2021");
        batchExeDTO.setBatchId(1L);
        batchExeDTO.setStepId(1L);
        batchExeDTO.setRealMakeNum(1L);
        batchExeDTO.setScanBy(1L);
        batchExeDTO.setScanTime(LocalDateTime.now());
        batchExeDTO.setSalary(new BigDecimal(100));
        batchExeDTO.setDelFlag(0);
        batchExeDTO.setProId(1L);
        batchExeDTO.setSizeId(1L);
        batchExeDTO.setColorId(1L);
        batchExeDTO.setEmpId(1L);
        batchExeDTO.setCreateBy(1L);
        batchExeDTO.setCreateTime(LocalDateTime.now());
        batchExeDTO.setUpdateBy(1L);
        batchExeDTO.setUpdateTime(LocalDateTime.now());
        batchExeDTO.setMakeStatus("1");
        batchExeDTO.setWkSalary(new BigDecimal(100));
        batchExeDTO.setId(1L);
        batchExeDTO.setColorName("黄色");
        batchExeDTO.setRealMakeNum(1L);
        batchExeDTO.setSizeName("XL");
        batchExeDTO.setStepName("生产");
        batchExeDTO.setUserName("张三");
        System.out.println(JSON.toJSONString(batchExeDTO));
    }
}



