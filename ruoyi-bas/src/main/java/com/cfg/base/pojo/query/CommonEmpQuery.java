package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单位信息 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="单位信息 查询 对象")
@Data
public class CommonEmpQuery {
    @ApiModelProperty("单位ID 精确匹配")
    private String empId;
    @ApiModelProperty("单位编码 精确匹配")
    private String empCode;

    @ApiModelProperty("单位名称 精确匹配")
    private String empNameLike;

    @ApiModelProperty("上级单位ID 精确匹配")
    private Long parentId;

    @ApiModelProperty("祖级列表 精确匹配")
    private String ancestors;

    @ApiModelProperty("排序号 精确匹配")
    private Long orderNum;

    /** 行政区划编码 */
    private String xzqhCode;
    /** 详细地址 */
    private String addr;
    /** 邀请码 */
    private String inviteCode;

}
