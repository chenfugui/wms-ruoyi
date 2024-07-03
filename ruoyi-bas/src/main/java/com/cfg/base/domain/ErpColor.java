package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装颜色管理对象 erp_color
 * 
 * @author chenfg
 */
@ApiModel(description="服装颜色管理对象")
@Data
@TableName("erp_color")
public class ErpColor extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("颜色id")
    private Long id;

    @ApiModelProperty("颜色编码")
    @Excel(name = "颜色编码")
    private String colorCode;

    @ApiModelProperty("颜色名称")
    @Excel(name = "颜色名称")
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
