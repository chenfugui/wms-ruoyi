package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 产品颜色管理对象 erp_pro_color
 * 
 * @author chenfg
 */
@ApiModel(description="产品颜色管理对象")
@Data
@TableName("erp_pro_color")
public class ErpProColor extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @Excel(name = "id")
    private Long id;

    @ApiModelProperty("产品ID")
    @Excel(name = "产品ID")
    private Long proId;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("尺码ID")
    private Long colorId;

    @ApiModelProperty("尺寸编码")
    @Excel(name = "尺寸编码")
    private String colorCode;

    @ApiModelProperty("尺寸名称")
    @Excel(name = "尺寸名称")
    private String colorName;

    @ApiModelProperty("排序号")
    @Excel(name = "排序号")
    private Long seqno;

    @ApiModelProperty("del_flag 0 正常  1  删除")
    private Integer delFlag;

}
