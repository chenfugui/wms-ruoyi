package com.cfg.base.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品生产表 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="产品生产表 查询 对象")
@Data
public class MakeQuery {
    @ApiModelProperty("生产编码 精确匹配")
    private String makeCode;

    @ApiModelProperty("生产数量 精确匹配")
    private Long makeNum;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

    @ApiModelProperty("状态 精确匹配")
    private String status;

    @ApiModelProperty("产品ID 精确匹配")
    private Long proId;

    @ApiModelProperty("数量")
    private Long genNum;

    @ApiModelProperty("产品名称")
    private String proName;

}
