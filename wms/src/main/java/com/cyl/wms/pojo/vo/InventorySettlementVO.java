package com.cyl.wms.pojo.vo;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 库存结算单 数据视图对象
 * 
 * @author zcc
 */
@Data
public class InventorySettlementVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 库存结算单号 */
    @Excel(name = "库存结算单号")
    private String inventorySettlementNo;
   /** 库存结算单状态11：结算中 22：已完成 */
    @Excel(name = "库存结算单状态11：结算中 22：已完成")
    private Integer inventorySettlementStatus;
   /** 库存结算周期开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "库存结算周期开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inventorySettlementStartTime;
   /** 库存结算周期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "库存结算周期结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inventorySettlementEndTime;
   /** 结算类型,1:月结，2:年结 */
    @Excel(name = "结算类型,1:月结，2:年结")
    private Integer settlementType;
   /** 备注 */
    @Excel(name = "备注")
    private String remark;
}
