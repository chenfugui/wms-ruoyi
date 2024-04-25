package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 工单信息 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class CommonWkbillVO extends BaseAudit {
   /** 工单ID */
    private Long wkbillId;
   /** 用户id */
    @Excel(name = "用户id")
    private Long userId;
   /** 工序ID */
    @Excel(name = "工序ID")
    private Long stepId;
   /** 生产数量 */
    @Excel(name = "生产数量")
    private Long mknum;
   /** 工价 */
    @Excel(name = "工价")
    private BigDecimal stepPrice;
   /** 应付工资 */
    @Excel(name = "应付工资")
    private BigDecimal payable;
   /** 实付工资 */
    @Excel(name = "实付工资")
    private BigDecimal actPay;
   /** 数据录入类型：1 扫码，2 手工录入 */
    @Excel(name = "数据录入类型：1 扫码，2 手工录入")
    private String inputType;
}
