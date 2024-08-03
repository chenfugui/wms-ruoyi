package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * app功能菜单 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpAppMenuDTO extends BaseAudit {
    private Long id;
    private String catalogId;
    private String catalogName;
    private String menuCode;
    private String menuName;
    private String menuPath;
    private Integer seqNo;
}
