package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装工序信息 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProProcessDTO extends BaseAudit {
    private Long id;
    private Long proId;
    private String empId;
    private Long stepId;
    private String stepCode;
    private String stepName;
    private BigDecimal price;
    private Long seqNo;
    private String status;
    private Integer delFlag;
}
