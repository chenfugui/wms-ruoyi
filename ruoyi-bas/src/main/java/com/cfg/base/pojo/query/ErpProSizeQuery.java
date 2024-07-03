package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装生产尺码 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装生产尺码 查询 对象")
@Data
public class ErpProSizeQuery {
    @ApiModelProperty("产品ID 精确匹配")
    private Long proId;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

    @ApiModelProperty("尺码ID 精确匹配")
    private Long sizeId;

    @ApiModelProperty("尺寸编码 精确匹配")
    private String sizeCode;

    @ApiModelProperty("尺寸名称 精确匹配")
    private String sizeNameLike;

    @ApiModelProperty("排序号 精确匹配")
    private Long seqNo;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
