package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @ApiModelProperty("PROID 精确匹配")
    private Long proid;

    @ApiModelProperty("STEPID 精确匹配")
    private Long stepid;

    @ApiModelProperty("SIZEID 精确匹配")
    private Long sizeid;

    @ApiModelProperty("EMP_ID 精确匹配")
    private Long empId;

    @ApiModelProperty("PRICE 精确匹配")
    private BigDecimal price;

    @ApiModelProperty("创建人 精确匹配")
    private Long creater;

    @ApiModelProperty("更新人 精确匹配")
    private Long updater;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
