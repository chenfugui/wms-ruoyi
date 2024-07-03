package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装产品管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装产品管理 查询 对象")
@Data
public class ErpProQuery {
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
    private Long empid;

    @ApiModelProperty("顺序号 精确匹配")
    private Long seqNo;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
