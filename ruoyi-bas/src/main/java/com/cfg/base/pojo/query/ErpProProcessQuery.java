package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装工序信息 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装工序信息 查询 对象")
@Data
public class ErpProProcessQuery {
    @ApiModelProperty("产品id 精确匹配")
    private Long proId;

    @ApiModelProperty("单位ID 精确匹配")
    private String empId;

    @ApiModelProperty("工序id 精确匹配")
    private Long stepId;

    @ApiModelProperty("工序编码 精确匹配")
    private String stepcode;

    @ApiModelProperty("工序名称 精确匹配")
    private String stepnameLike;

    @ApiModelProperty("工序工价 精确匹配")
    private BigDecimal price;

    @ApiModelProperty("排序号 精确匹配")
    private Long seqno;

    @ApiModelProperty("工序状态 精确匹配")
    private String status;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
