package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 模板项目明细管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProTemplateDetailDTO extends BaseAudit {
    private Long id;
    private Long tmpId;
    private String tmpCode;
    private String itemCode;
    private String itemName;
    private String itemMemo;
    private Long seqNo;
    private Long empId;
}
