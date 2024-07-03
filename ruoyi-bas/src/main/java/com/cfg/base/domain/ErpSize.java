package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装尺码管理对象 erp_size
 * 
 * @author chenfg
 */
@ApiModel(description="服装尺码管理对象")
@Data
@TableName("erp_size")
public class ErpSize extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("尺码id")
    private Long id;

    @ApiModelProperty("尺码编码")
    @Excel(name = "尺码编码")
    private String colorCode;

    @ApiModelProperty("尺码名称")
    @Excel(name = "尺码名称")
    private String colorName;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("顺序号")
    @Excel(name = "顺序号")
    private Long seqNo;

    @ApiModelProperty("dr 1 正常  2  删除")
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;

}
