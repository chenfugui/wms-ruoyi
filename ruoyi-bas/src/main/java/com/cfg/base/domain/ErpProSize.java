package com.cfg.base.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装生产尺码对象 erp_pro_size
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产尺码对象")
@Data
@TableName("erp_pro_size")
public class ErpProSize {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("产品ID")
    @Excel(name = "产品ID")
    private Long proId;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("尺码ID")
    @Excel(name = "尺码ID")
    private Long sizeId;

    @ApiModelProperty("尺寸编码")
    @Excel(name = "尺寸编码")
    private String sizeCode;

    @ApiModelProperty("尺寸名称")
    @Excel(name = "尺寸名称")
    private String sizeName;

    @ApiModelProperty("排序号")
    @Excel(name = "排序号")
    private Long seqno;

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
