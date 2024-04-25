package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工单信息 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="工单信息 查询 对象")
@Data
public class CommonWkbillQuery {
    @ApiModelProperty("用户id 精确匹配")
    private Long userId;

    @ApiModelProperty("工序ID 精确匹配")
    private Long stepId;

    @ApiModelProperty("生产数量 精确匹配")
    private Long mknum;

    @ApiModelProperty("工价 精确匹配")
    private BigDecimal stepPrice;

    @ApiModelProperty("应付工资 精确匹配")
    private BigDecimal payable;

    @ApiModelProperty("实付工资 精确匹配")
    private BigDecimal actPay;

    @ApiModelProperty("数据录入类型：1 扫码，2 手工录入 精确匹配")
    private String inputType;

}
