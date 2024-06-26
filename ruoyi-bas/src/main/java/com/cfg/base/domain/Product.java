package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 产品信息表对象 bas_product
 * 
 * @author chenfg
 */
@ApiModel(description="产品信息表对象")
@Data
@TableName("bas_product")
public class Product extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品id")
    private Long proId;

    @ApiModelProperty("产品编码")
    @Excel(name = "产品编码")
    private String proCode;

    @ApiModelProperty("产品名称")
    @Excel(name = "产品名称")
    private String proName;

    @ApiModelProperty("产品描述")
    @Excel(name = "产品描述")
    private String proDesc;

    @ApiModelProperty("产品类型")
    @Excel(name = "产品类型")
    private String proType;

    @ApiModelProperty("产品备注")
    @Excel(name = "产品备注")
    private String proMemo;

    @ApiModelProperty("产品状态")
    @Excel(name = "产品状态")
    private String status;

    @ApiModelProperty("删除标志")
    private String delFlag;

    @ApiModelProperty("单位ID")
    @Excel(name = "单位ID")
    private Long empId;

}
