package com.cfg.base.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装工价信息对象 erp_pro_price
 * 
 * @author chenfg
 */
@ApiModel(description="服装工价信息对象")
@Data
@TableName("erp_pro_price")
public class ErpProPrice extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("PRO_ID")
    @Excel(name = "PRO_ID")
    private Long proId;

    @ApiModelProperty("STEP_ID")
    @Excel(name = "STEP_ID")
    private Long stepId;

    @ApiModelProperty("SIZE_ID")
    @Excel(name = "SIZE_ID")
    private Long sizeId;

    @ApiModelProperty("EMP_ID")
    @Excel(name = "EMP_ID")
    private Long empId;

    @ApiModelProperty("PRICE")
    @Excel(name = "PRICE")
    private BigDecimal price;

    @ApiModelProperty("dr 1 正常  2  删除")
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;

}
