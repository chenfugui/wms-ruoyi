package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装生产明细 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装生产明细 查询 对象")
@Data
public class ErpProMakeDetailQuery {
    @ApiModelProperty("生产ID 精确匹配")
    private Long proMakeId;

    @ApiModelProperty("生产编码 精确匹配")
    private String proMakeNo;

    @ApiModelProperty("产品id 精确匹配")
    private Long proId;

    @ApiModelProperty("尺码ID 精确匹配")
    private Long sizeId;

    @ApiModelProperty("颜色ID 精确匹配")
    private Long colorId;

    @ApiModelProperty("布料ID 精确匹配")
    private Long clothId;

    @ApiModelProperty("布料编码 精确匹配")
    private String clothCode;

    @ApiModelProperty("布料名称 精确匹配")
    private String clothNameLike;

    @ApiModelProperty("生产数量 精确匹配")
    private Long makeNum;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

}
