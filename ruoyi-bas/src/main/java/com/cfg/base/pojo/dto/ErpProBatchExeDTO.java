package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装生产进度 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProBatchExeDTO {
    private Long id;
    private Long batchId;
    private Long stepId;
    private String makeStatus;
    private Long realMakeNum;
    private Long empId;
    private Long scanBy;
    private LocalDateTime scanTime;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private BigDecimal salary;
    private Integer dr;
}
