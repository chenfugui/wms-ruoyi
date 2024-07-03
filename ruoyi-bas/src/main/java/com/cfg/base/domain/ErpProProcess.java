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
 * 服装工序信息对象 erp_pro_process
 * 
 * @author chenfg
 */
@ApiModel(description="服装工序信息对象")
@Data
@TableName("erp_pro_process")
public class ErpProProcess extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工序id")
    private Long id;

    @ApiModelProperty("产品id")
    @Excel(name = "产品id")
    private Long proId;

    @ApiModelProperty("单位ID")
    @Excel(name = "单位ID")
    private String empId;

    @ApiModelProperty("工序id")
    @Excel(name = "工序id")
    private Long stepId;

    @ApiModelProperty("工序编码")
    @Excel(name = "工序编码")
    private String stepCode;

    @ApiModelProperty("工序名称")
    @Excel(name = "工序名称")
    private String stepName;

    @ApiModelProperty("工序工价")
    @Excel(name = "工序工价")
    private BigDecimal price;

    @ApiModelProperty("排序号")
    @Excel(name = "排序号")
    private Long seqNo;

    @ApiModelProperty("工序状态")
    @Excel(name = "工序状态")
    private String status;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;

}
