package com.cfg.base.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装生产明细对象 erp_pro_make_detail
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产明细对象")
@Data
@TableName("erp_pro_make_detail")
public class ErpProMakeDetail {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("生产ID")
    @Excel(name = "生产ID")
    private Long proMakeId;

    @ApiModelProperty("生产编码")
    @Excel(name = "生产编码")
    private String proMakeNo;

    @ApiModelProperty("产品id")
    @Excel(name = "产品id")
    private Long proId;

    @ApiModelProperty("尺码ID")
    @Excel(name = "尺码ID")
    private Long sizeId;

    @ApiModelProperty("颜色ID")
    @Excel(name = "颜色ID")
    private Long colorId;

    @ApiModelProperty("布料ID")
    @Excel(name = "布料ID")
    private Long clothId;

    @ApiModelProperty("布料编码")
    @Excel(name = "布料编码")
    private String clothCode;

    @ApiModelProperty("布料名称")
    @Excel(name = "布料名称")
    private String clothName;

    @ApiModelProperty("生产数量")
    @Excel(name = "生产数量")
    private Long makeNum;

    @ApiModelProperty("创建人")
    @Excel(name = "创建人")
    private Long creater;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    @Excel(name = "更新人")
    private Long updater;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("dr 1 正常  2  删除")
    @Excel(name = "dr 1 正常  2  删除")
    private Integer dr;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

}
