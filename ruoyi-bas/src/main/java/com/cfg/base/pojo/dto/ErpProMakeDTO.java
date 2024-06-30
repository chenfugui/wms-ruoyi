package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import lombok.Data;
/**
 * 服装生产管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProMakeDTO {
    private Long id;
    private Long proId;
    private String proMakeNo;
    private LocalDateTime makeStartTime;
    private LocalDateTime makeEndTime;
    private LocalDateTime deliverTime;
    private String status;
    private Long empId;
    private Long seqNo;
    private Long creater;
    private LocalDateTime createTime;
    private Long updater;
    private LocalDateTime updateTime;
    private Integer dr;
}
