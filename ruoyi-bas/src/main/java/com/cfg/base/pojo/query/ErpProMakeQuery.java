package com.cfg.base.pojo.query;

import java.time.LocalDateTime;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服装生产管理 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="服装生产管理 查询 对象")
@Data
public class ErpProMakeQuery {
    @ApiModelProperty("产品id 精确匹配")
    private Long proId;

    @ApiModelProperty("产品生产编码 精确匹配")
    private String proMakeNo;

    @ApiModelProperty("生产开始时间 精确匹配")
    private LocalDateTime makeStartTime;

    @ApiModelProperty("生产结束时间 精确匹配")
    private LocalDateTime makeEndTime;

    @ApiModelProperty("交付时间 精确匹配")
    private LocalDateTime deliverTime;

    @ApiModelProperty("生产状态: 1 正常  2 停止 3 作废 精确匹配")
    private String status;

    @ApiModelProperty("单位id 精确匹配")
    private Long empId;

    @ApiModelProperty("顺序号 精确匹配")
    private Long seqNo;

    @ApiModelProperty("创建人 精确匹配")
    private Long creater;

    @ApiModelProperty("更新人 精确匹配")
    private Long updater;

    @ApiModelProperty("dr 1 正常  2  删除 精确匹配")
    private Integer dr;

}
