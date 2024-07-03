package com.cfg.base.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装生产进度对象 erp_pro_batch_exe
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产进度对象")
@Data
@TableName("erp_pro_batch_exe")
public class ErpProBatchExe extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("进度ID")
    private Long id;

    @ApiModelProperty("生产批次")
    @Excel(name = "生产批次")
    private Long batchId;

    @ApiModelProperty("工序Id")
    @Excel(name = "工序Id")
    private Long stepId;

    @ApiModelProperty("生产状态")
    @Excel(name = "生产状态")
    private String makeStatus;

    @ApiModelProperty("实际生产数量")
    @Excel(name = "实际生产数量")
    private Long realMakeNum;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("扫描人")
    @Excel(name = "扫描人")
    private Long scanBy;

    @ApiModelProperty("扫描时间")
    @Excel(name = "扫描时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scanTime;

    @ApiModelProperty("工资")
    @Excel(name = "工资")
    private BigDecimal salary;

    @ApiModelProperty("dr 1 正常  2  删除")
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;

}
