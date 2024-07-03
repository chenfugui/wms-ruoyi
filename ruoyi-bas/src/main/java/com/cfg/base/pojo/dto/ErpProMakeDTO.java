package com.cfg.base.pojo.dto;

import java.time.LocalDateTime;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProMakeDTO extends BaseAudit {
    private Long id;
    private Long proId;
    private String proMakeNo;
    private LocalDateTime makeStartTime;
    private LocalDateTime makeEndTime;
    private LocalDateTime deliverTime;
    private String status;
    private Long empId;
    private Long seqNo;
    private Integer dr;
}
