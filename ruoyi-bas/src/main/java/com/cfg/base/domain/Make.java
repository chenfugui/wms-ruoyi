package com.cfg.base.domain;

import java.time.LocalDateTime;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 产品生产表对象 bas_make
 * 
 * @author chenfg
 */
@ApiModel(description="产品生产表对象")
@Data
@TableName("bas_make")
public class Make {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("生产id")
    private Long makeId;

    @ApiModelProperty("生产编码")
    @Excel(name = "生产编码")
    private String makeCode;

    @ApiModelProperty("生产数量")
    @Excel(name = "生产数量")
    private Long makeNum;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

    @ApiModelProperty("状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty("删除标志")
    private String delFlag;

    @ApiModelProperty("创建人")
    private Long createBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人")
    private Long updateBy;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    @ApiModelProperty("产品ID")
    @Excel(name = "产品ID")
    private Long proId;

}
