package com.cyl.wms.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 库存结算单 查询 对象
 *
 * @author zcc
 */
@ApiModel(description="库存结算单 查询 对象")
@Data
public class InventorySettlementQuery {
    @ApiModelProperty("库存结算单号 精确匹配")
    private String inventorySettlementNo;

    @ApiModelProperty("库存结算单状态11：结算中 22：已完成 精确匹配")
    private Integer inventorySettlementStatus;

    @ApiModelProperty("库存结算周期开始时间 精确匹配")
    private LocalDateTime inventorySettlementStartTime;

    @ApiModelProperty("库存结算周期结束时间 精确匹配")
    private LocalDateTime inventorySettlementEndTime;

    @ApiModelProperty("结算类型,1:月结，2:年结 精确匹配")
    private Integer settlementType;

}
