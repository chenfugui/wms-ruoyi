package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装工价信息 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装工价信息 查询 对象")
@Data
public class ErpProPriceQuery {
    @ApiModelProperty("PRO_ID 精确匹配")
    private Long proId;

    @ApiModelProperty("STEP_ID 精确匹配")
    private Long stepId;

    @ApiModelProperty("SIZE_ID 精确匹配")
    private Long sizeId;

    @ApiModelProperty("EMP_ID 精确匹配")
    private Long empId;

    @ApiModelProperty("PRICE 精确匹配")
    private BigDecimal price;

    @ApiModelProperty("delFlag 0 正常  1  删除 精确匹配")
    private Integer delFlag;

}
