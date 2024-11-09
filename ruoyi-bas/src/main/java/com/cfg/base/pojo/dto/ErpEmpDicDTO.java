package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 常用字典管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpEmpDicDTO extends BaseAudit {
    private Integer id;
    private String typeCode;
    private String typeName;
    private String itemCode;
    private String itemName;
    private String itemValue;
    private Long empId;
}
