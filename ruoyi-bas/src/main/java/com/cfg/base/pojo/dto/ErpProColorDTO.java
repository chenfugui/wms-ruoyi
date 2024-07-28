package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 产品颜色管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProColorDTO extends BaseAudit {
    private Long id;
    private Long proId;
    private Long empId;
    private Long colorId;
    private String colorCode;
    private String colorName;
    private Long seqno;
}
