package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 工单信息 DTO 对象
 *
 * @author chenfg
 */
@Data
public class CommonWkbillDTO extends BaseAudit {
    private Long wkbillId;
    private Long userId;
    private Long stepId;
    private Long mknum;
    private BigDecimal stepPrice;
    private BigDecimal payable;
    private BigDecimal actPay;
    private String inputType;
}
