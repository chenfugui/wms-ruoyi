package com.cfg.base.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 服装工价信息对象 erp_pro_price
 * 
 * @author chenfg
 */
@ApiModel(description="服装工价信息对象")
@Data
@TableName("erp_pro_price")
public class ErpProPrice {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("PROID")
    @Excel(name = "PROID")
    private Long proid;

    @ApiModelProperty("STEPID")
    @Excel(name = "STEPID")
    private Long stepid;

    @ApiModelProperty("SIZEID")
    @Excel(name = "SIZEID")
    private Long sizeid;

    @ApiModelProperty("EMP_ID")
    @Excel(name = "EMP_ID")
    private Long empId;

    @ApiModelProperty("PRICE")
    @Excel(name = "PRICE")
    private BigDecimal price;

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

}
