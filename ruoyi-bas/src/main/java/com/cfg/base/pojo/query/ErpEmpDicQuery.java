package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 常用字典管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="常用字典管理 查询 对象")
@Data
public class ErpEmpDicQuery {
    @ApiModelProperty("类型编码 精确匹配")
    private String typeCode;

    @ApiModelProperty("类型名称 精确匹配")
    private String typeNameLike;

    @ApiModelProperty("字典项编码 精确匹配")
    private String itemCode;

    @ApiModelProperty("字典项名称 精确匹配")
    private String itemNameLike;

    @ApiModelProperty("字典项值 精确匹配")
    private String itemValue;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

}
