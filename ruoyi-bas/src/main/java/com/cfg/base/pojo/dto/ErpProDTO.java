package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装产品管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProDTO extends BaseAudit {
    private Long proId;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proType;
    private String proMemo;
    private String status;
    private Long empid;
    private Long seqNo;
    private Integer delFlag;
}
