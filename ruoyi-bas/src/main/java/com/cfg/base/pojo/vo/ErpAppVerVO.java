package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * App版本 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpAppVerVO extends BaseAudit {
   /** ID */
    private Long id;
   /** 应用版本 */
    @Excel(name = "应用版本")
    private String appVersion;
   /** 发布编号 */
    @Excel(name = "发布编号")
    private Long releaseNum;
   /** 版本信息 */
    @Excel(name = "版本信息")
    private String appInfo;
   /** 苹果地址 */
    @Excel(name = "苹果地址")
    private String iosUrl;
   /** 安卓地址 */
    @Excel(name = "安卓地址")
    private String androidUrl;
}
