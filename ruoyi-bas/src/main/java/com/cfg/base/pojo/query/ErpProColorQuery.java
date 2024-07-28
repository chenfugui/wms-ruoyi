package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品颜色管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="产品颜色管理 查询 对象")
@Data
public class ErpProColorQuery {
    @ApiModelProperty("产品ID 精确匹配")
    private Long proId;

    @ApiModelProperty("尺寸编码 精确匹配")
    private String colorCode;

    @ApiModelProperty("尺寸名称 精确匹配")
    private String colorNameLike;

}
