package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 单位信息 DTO 对象
 *
 * @author chenfg
 */
@Data
public class CommonEmpDTO extends BaseAudit {
    private Long empId;
    private String empCode;
    private String empName;
    private String empType;
    private Long parentId;
    private String ancestors;
    private Long orderNum;
    private String status;
    /** 行政区划编码 */
    private String xzqhCode;
    /** 详细地址 */
    private String addr;
    /** 邀请码 */
    private String inviteCode;
}
