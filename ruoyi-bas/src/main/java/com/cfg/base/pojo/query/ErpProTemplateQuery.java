package com.cfg.base.pojo.query;

import java.util.List;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模板表 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="模板表 查询 对象")
@Data
public class ErpProTemplateQuery {
    @ApiModelProperty("模板编码 精确匹配")
    private String tmpCode;

    @ApiModelProperty("模板名称 精确匹配")
    private String tmpNameLike;

    @ApiModelProperty("模板类型 精确匹配")
    private String tmpType;

    @ApiModelProperty("备注 精确匹配")
    private String tmpMemo;

}
