package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装尺码管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装尺码管理 查询 对象")
@Data
public class ErpSizeQuery {
    @ApiModelProperty("尺码编码 精确匹配")
    private String colorCode;

    @ApiModelProperty("尺码名称 精确匹配")
    private String colorNameLike;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

    @ApiModelProperty("顺序号 精确匹配")
    private Long seqNo;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
