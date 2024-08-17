package com.cfg.base.domain;

import com.cfg.api.DelFlagSetterApi;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装生产尺码对象 erp_pro_size
 * 
 * @author chenfg
 */
@ApiModel(description="服装生产尺码对象")
@Data
@TableName("erp_pro_size")
public class ErpProSize extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("产品ID")
    @Excel(name = "产品ID")
    private Long proId;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("尺码ID")
    @Excel(name = "尺码ID")
    private Long sizeId;

    @ApiModelProperty("尺寸编码")
    @Excel(name = "尺寸编码")
    private String sizeCode;

    @ApiModelProperty("尺寸名称")
    @Excel(name = "尺寸名称")
    private String sizeName;

    @ApiModelProperty("排序号")
    @Excel(name = "排序号")
    private Long seqNo;

    @ApiModelProperty("delFlag 0 正常  1  删除")
    @Excel(name = "delFlag 0 正常  1  删除")
    private Integer delFlag=0;

}
