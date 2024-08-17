package com.cfg.base.domain;

import java.math.BigDecimal;

import com.cfg.api.DelFlagSetterApi;
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

    @ApiModelProperty("产品")
    @Excel(name = "产品")
    private Long proId;

    @ApiModelProperty("工序")
    @Excel(name = "工序")
    private Long stepId;

    @ApiModelProperty("尺码")
    @Excel(name = "尺码")
    private Long sizeId;

    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private Long empId;

    @ApiModelProperty("工价")
    @Excel(name = "工价")
    private BigDecimal price;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag=0;

}
