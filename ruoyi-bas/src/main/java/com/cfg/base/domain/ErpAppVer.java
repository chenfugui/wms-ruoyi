package com.cfg.base.domain;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * App版本对象 erp_app_ver
 * 
 * @author chenfg
 */
@ApiModel(description="App版本对象")
@Data
@TableName("erp_app_ver")
public class ErpAppVer extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("应用版本")
    @Excel(name = "应用版本")
    private String appVersion;

    @ApiModelProperty("发布编号")
    @Excel(name = "发布编号")
    private Long releaseNum;

    @ApiModelProperty("版本信息")
    @Excel(name = "版本信息")
    private String appInfo;

    @ApiModelProperty("苹果地址")
    @Excel(name = "苹果地址")
    private String iosUrl;

    @ApiModelProperty("安卓地址")
    @Excel(name = "安卓地址")
    private String androidUrl;

    @ApiModelProperty("del_flag 0 正常  1  删除")
    private Integer delFlag;

    public static void main(String[] args) {
        ErpAppVer erpAppVer = new ErpAppVer();
        erpAppVer.setAppVersion("1.0.0");
        erpAppVer.setReleaseNum(1L);
        erpAppVer.setAppInfo("1.0.0版本");
        erpAppVer.setIosUrl("https://www.baidu.com");
        erpAppVer.setAndroidUrl("https://www.baidu.com");
        erpAppVer.setCreateTime(LocalDateTime.now());
        erpAppVer.setUpdateTime(LocalDateTime.now());
        System.out.println(JSON.toJSONString(erpAppVer));
    }
}
