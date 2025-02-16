package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模板项目明细管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="模板项目明细管理 查询 对象")
@Data
public class ErpProTemplateDetailQuery {
    @ApiModelProperty("模板 精确匹配")
    private Long tmpId;

    @ApiModelProperty("模板编码 精确匹配")
    private String tmpCode;

    @ApiModelProperty("项目编码 精确匹配")
    private String itemCode;

    @ApiModelProperty("项目名称 精确匹配")
    private String itemNameLike;

    @ApiModelProperty("备注 精确匹配")
    private String itemMemo;

}
