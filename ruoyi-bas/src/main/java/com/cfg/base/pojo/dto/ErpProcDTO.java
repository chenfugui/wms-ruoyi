package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装工序管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProcDTO extends BaseAudit {
    private Long id;
    private String stepCode;
    private String stepName;
    private BigDecimal stepPrice;
    private String empCode;
    private Long seqNo;
    private String status;
    private Integer dr;
    private Long empId;
}
