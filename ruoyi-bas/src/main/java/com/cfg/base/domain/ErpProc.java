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
 * 服装工序管理对象 erp_proc
 * 
 * @author chenfg
 */
@ApiModel(description="服装工序管理对象")
@Data
@TableName("erp_proc")
public class ErpProc extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工序id")
    private Long id;

    @ApiModelProperty("工序编码")
    @Excel(name = "工序编码")
    private String stepCode;

    @ApiModelProperty("工序名称")
    @Excel(name = "工序名称")
    private String stepName;

    @ApiModelProperty("工序工价")
    @Excel(name = "工序工价")
    private BigDecimal stepPrice;

    @ApiModelProperty("单位编码")
    @Excel(name = "单位编码")
    private String empCode;

    @ApiModelProperty("顺序号")
    @Excel(name = "顺序号")
    private Long seqNo;

    @ApiModelProperty("工序状态")
    @Excel(name = "工序状态")
    private String status;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

}
