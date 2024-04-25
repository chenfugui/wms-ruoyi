package com.cfg.base.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 工单信息对象 bas_common_wkbill
 * 
 * @author chenfg
 */
@ApiModel(description="工单信息对象")
@Data
@TableName("bas_common_wkbill")
public class CommonWkbill extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工单ID")
    private Long wkbillId;

    @ApiModelProperty("用户id")
    @Excel(name = "用户id")
    private Long userId;

    @ApiModelProperty("工序ID")
    @Excel(name = "工序ID")
    private Long stepId;

    @ApiModelProperty("生产数量")
    @Excel(name = "生产数量")
    private Long mknum;

    @ApiModelProperty("工价")
    @Excel(name = "工价")
    private BigDecimal stepPrice;

    @ApiModelProperty("应付工资")
    @Excel(name = "应付工资")
    private BigDecimal payable;

    @ApiModelProperty("实付工资")
    @Excel(name = "实付工资")
    private BigDecimal actPay;

    @ApiModelProperty("数据录入类型：1 扫码，2 手工录入")
    @Excel(name = "数据录入类型：1 扫码，2 手工录入")
    private String inputType;
    @ApiModelProperty("删除标志：0 未删除，1 已删除")
    @Excel(name = "删除标志：0 未删除，1 已删除")
    private String delFlag;

}
