package com.cfg.base.pojo.vo;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装工价信息 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpProPriceVO extends BaseAudit {
   /** ID */
    private Long id;
   /** PRO_ID */
    @Excel(name = "PRO_ID")
    private Long proId;
   /** STEP_ID */
    @Excel(name = "STEP_ID")
    private Long stepId;
   /** SIZE_ID */
    @Excel(name = "SIZE_ID")
    private Long sizeId;
   /** EMP_ID */
    @Excel(name = "EMP_ID")
    private Long empId;
   /** PRICE */
    @Excel(name = "PRICE")
    private BigDecimal price;
    /** 数据状态 1 正常  2  删除 */
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;
}
