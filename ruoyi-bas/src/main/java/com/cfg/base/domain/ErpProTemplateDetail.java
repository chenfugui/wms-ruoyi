package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 模板项目明细管理对象 erp_pro_template_detail
 * 
 * @author chenfg
 */
@ApiModel(description="模板项目明细管理对象")
@Data
@TableName("erp_pro_template_detail")
public class ErpProTemplateDetail extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("模板")
    @Excel(name = "模板")
    private Long tmpId;

    @ApiModelProperty("模板编码")
    @Excel(name = "模板编码")
    private String tmpCode;

    @ApiModelProperty("项目编码")
    @Excel(name = "项目编码")
    private String itemCode;

    @ApiModelProperty("项目名称")
    @Excel(name = "项目名称")
    private String itemName;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String itemMemo;

    @ApiModelProperty("顺序号")
    private Long seqNo;

    @ApiModelProperty("删除标志")
    private Integer delFlag;

    @ApiModelProperty("单位ID")
    private Long empId;

}
