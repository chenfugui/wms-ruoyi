package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * App版本 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpAppVerDTO extends BaseAudit {
    private Long id;
    private String appVersion;
    private Long releaseNum;
    private String appInfo;
    private String iosUrl;
    private String androidUrl;
}
