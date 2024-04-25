package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工序信息 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="工序信息 查询 对象")
@Data
public class CommonStepQuery {
    @ApiModelProperty("工序编码 精确匹配")
    private String stepCode;

    @ApiModelProperty("工序名称 精确匹配")
    private String stepNameLike;

    @ApiModelProperty("工价 精确匹配")
    private BigDecimal stepPrice;

    @ApiModelProperty("单位编码 精确匹配")
    private String empCode;

    @ApiModelProperty("序号 精确匹配")
    private Long stepSort;

    @ApiModelProperty("状态 精确匹配")
    private String status;

}
