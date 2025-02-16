package com.cfg.base.pojo.dto;

import java.util.List;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 模板表 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProTemplateDTO extends BaseAudit {
    private Long id;
    private String tmpCode;
    private String tmpName;
    private String tmpType;
    private String tmpMemo;
    private Long empId;
    private List<ErpProTemplateDetailDTO> erpProTemplateDetailList;
}
