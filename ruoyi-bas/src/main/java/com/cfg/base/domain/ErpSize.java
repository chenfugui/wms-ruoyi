package com.cfg.base.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class ErpSize {
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

    @ApiModelProperty("创建人")
    @Excel(name = "创建人")
    private Long creater;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    @Excel(name = "更新人")
    private Long updater;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("dr 1 正常  2  删除")
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;

}
