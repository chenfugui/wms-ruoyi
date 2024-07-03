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
    private String stepCode;

    @ApiModelProperty("工序名称 精确匹配")
    private String stepNameLike;

    @ApiModelProperty("工序工价 精确匹配")
    private BigDecimal price;

    @ApiModelProperty("排序号 精确匹配")
    private Long seqNo;

    @ApiModelProperty("工序状态 精确匹配")
    private String status;

    @ApiModelProperty("delFlag 0 正常  1  删除 精确匹配")
    private Integer delFlag;

}
