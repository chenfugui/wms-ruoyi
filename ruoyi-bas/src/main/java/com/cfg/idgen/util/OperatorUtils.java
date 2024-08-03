package com.cfg.idgen.util;

import com.ruoyi.common.core.domain.BaseAudit;
import com.ruoyi.common.utils.SecurityUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName : OperatorUtils
 * @Description : 操作人工具类
 * @Author : chenfg
 * @Date: 2024-08-03 16:54
 */
public class OperatorUtils {

    /***
     * @author chenfg
     * @date: 2024/8/3 16:56
     * @description:  设置创建信息
     * @param baseAudit
     * @return: void
     */
    public static void setCreateInfo(BaseAudit baseAudit){
        LocalDateTime now = LocalDateTime.now();
        baseAudit.setCreateTime(now);
        baseAudit.setUpdateTime(now);
        baseAudit.setCreateBy(SecurityUtils.getUserId());
        baseAudit.setUpdateBy(SecurityUtils.getUserId());
    }

    /***
     * @author chenfg
     * @date: 2024/8/3 16:56
     * @description:  设置更新信息
     * @param baseAudit
     * @return: void
     */
    public static void setUpdateInfo(BaseAudit baseAudit){
        LocalDateTime now = LocalDateTime.now();
        baseAudit.setUpdateTime(now);
        baseAudit.setUpdateBy(SecurityUtils.getUserId());
    }
}
