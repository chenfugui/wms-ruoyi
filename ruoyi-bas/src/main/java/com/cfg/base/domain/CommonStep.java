package com.cfg.base.domain;

import java.math.BigDecimal;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 工序信息对象 bas_common_step
 * 
 * @author chenfg
 */
@ApiModel(description="工序信息对象")
@Data
@TableName("bas_common_step")
public class CommonStep extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("工序id")
    private Long stepId;

    @ApiModelProperty("工序编码")
    @Excel(name = "工序编码")
    private String stepCode;

    @ApiModelProperty("工序名称")
    @Excel(name = "工序名称")
    private String stepName;

    @ApiModelProperty("工价")
    @Excel(name = "工价")
    private BigDecimal stepPrice;

    @ApiModelProperty("单位编码")
    @Excel(name = "单位编码")
    private String empCode;

    @ApiModelProperty("序号")
    @Excel(name = "序号")
    private Long stepSort;

    @ApiModelProperty("状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty("删除标志")
    private String delFlag;

}
