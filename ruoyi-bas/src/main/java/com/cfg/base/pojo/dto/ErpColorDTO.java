package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装颜色管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpColorDTO extends BaseAudit {
    private Long id;
    private String colorCode;
    private String colorName;
    private Long empId;
    private Long seqNo;
    private Integer delFlag;
}
