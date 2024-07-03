package com.cfg.base.domain;

import java.time.LocalDateTime;

import com.cfg.api.DelFlagSetterApi;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装生产管理对象 erp_pro_make
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产管理对象")
@Data
@TableName("erp_pro_make")
public class ErpProMake extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("产品id")
    @Excel(name = "产品id")
    private Long proId;

    @ApiModelProperty("产品生产编码")
    @Excel(name = "产品生产编码")
    private String proMakeNo;

    @ApiModelProperty("生产开始时间")
    @Excel(name = "生产开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime makeStartTime;

    @ApiModelProperty("生产结束时间")
    @Excel(name = "生产结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime makeEndTime;

    @ApiModelProperty("交付时间")
    @Excel(name = "交付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliverTime;

    @ApiModelProperty("生产状态: 1 正常  2 停止 3 作废")
    @Excel(name = "生产状态: 1 正常  2 停止 3 作废")
    private String status;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("顺序号")
    @Excel(name = "顺序号")
    private Long seqNo;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag;

}
