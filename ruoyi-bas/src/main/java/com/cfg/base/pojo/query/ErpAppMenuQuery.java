package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * app功能菜单 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="app功能菜单 查询 对象")
@Data
public class ErpAppMenuQuery {
    @ApiModelProperty("目录名称 精确匹配")
    private String catalogNameLike;

    @ApiModelProperty("菜单编码 精确匹配")
    private String menuCode;

    @ApiModelProperty("菜单名称 精确匹配")
    private String menuNameLike;

    @ApiModelProperty("菜单路径 精确匹配")
    private String menuPath;

}
