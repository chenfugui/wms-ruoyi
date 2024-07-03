package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装工序管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装工序管理 查询 对象")
@Data
public class ErpProcQuery {
    @ApiModelProperty("工序编码 精确匹配")
    private String stepCode;

    @ApiModelProperty("工序名称 精确匹配")
    private String stepNameLike;

    @ApiModelProperty("工序工价 精确匹配")
    private BigDecimal stepPrice;

    @ApiModelProperty("单位编码 精确匹配")
    private String empCode;

    @ApiModelProperty("顺序号 精确匹配")
    private Long seqNo;

    @ApiModelProperty("工序状态 精确匹配")
    private String status;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

}
