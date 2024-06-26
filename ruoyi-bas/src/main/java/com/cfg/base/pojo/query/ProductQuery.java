package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品信息表 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="产品信息表 查询 对象")
@Data
public class ProductQuery {
    @ApiModelProperty("产品编码 精确匹配")
    private String proCode;

    @ApiModelProperty("产品名称 精确匹配")
    private String proNameLike;

    @ApiModelProperty("产品描述 精确匹配")
    private String proDesc;

    @ApiModelProperty("产品类型 精确匹配")
    private String proType;

    @ApiModelProperty("产品备注 精确匹配")
    private String proMemo;

    @ApiModelProperty("产品状态 精确匹配")
    private String status;

    @ApiModelProperty("单位ID 精确匹配")
    private Long empId;

}
