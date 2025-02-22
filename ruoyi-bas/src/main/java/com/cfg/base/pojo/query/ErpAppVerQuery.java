package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * App版本 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="App版本 查询 对象")
@Data
public class ErpAppVerQuery {
    @ApiModelProperty("应用版本 精确匹配")
    private String appVersion;

    @ApiModelProperty("发布编号 精确匹配")
    private Long releaseNum;

    @ApiModelProperty("版本信息 精确匹配")
    private String appInfo;

    @ApiModelProperty("苹果地址 精确匹配")
    private String iosUrl;

    @ApiModelProperty("安卓地址 精确匹配")
    private String androidUrl;

}
