package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 工序信息 DTO 对象
 *
 * @author chenfg
 */
@Data
public class CommonStepDTO extends BaseAudit {
    private Long stepId;
    private String stepCode;
    private String stepName;
    private BigDecimal stepPrice;
    private String empCode;
    private Long stepSort;
    private String status;
}
