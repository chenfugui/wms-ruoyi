package com.cyl.wms.pojo.dto;

import java.time.LocalDateTime;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存结算单 DTO 对象
 *
 * @author zcc
 */
@Data
public class InventorySettlementDTO extends BaseAudit {
    private Long id;
    private String inventorySettlementNo;
    private Integer inventorySettlementStatus;
    private LocalDateTime inventorySettlementStartTime;
    private LocalDateTime inventorySettlementEndTime;
    private Integer settlementType;
    private String remark;
}
