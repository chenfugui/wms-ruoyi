package com.cfg.base.pojo.dto;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装工价信息 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProPriceDTO extends BaseAudit {
    private Long id;
    private Long proId;
    private Long stepId;
    private Long sizeId;
    private Long empId;
    private BigDecimal price;
    private Integer delFlag;
}
