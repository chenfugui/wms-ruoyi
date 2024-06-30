package com.cfg.base.pojo.query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装生产进度 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装生产进度 查询 对象")
@Data
public class ErpProBatchExeQuery {
    @ApiModelProperty("生产批次 精确匹配")
    private Long batchId;

    @ApiModelProperty("工序Id 精确匹配")
    private Long stepId;

    @ApiModelProperty("生产状态 精确匹配")
    private String makeStatus;

    @ApiModelProperty("实际生产数量 精确匹配")
    private Long realMakeNum;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

    @ApiModelProperty("扫描人 精确匹配")
    private Long scanBy;

    @ApiModelProperty("扫描时间 精确匹配")
    private LocalDateTime scanTime;

    @ApiModelProperty("创建人 精确匹配")
    private Long creater;

    @ApiModelProperty("更新人 精确匹配")
    private Long updater;

    @ApiModelProperty("工资 精确匹配")
    private BigDecimal salary;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
