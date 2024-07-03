package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产尺码 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProSizeDTO extends BaseAudit {
    private Long id;
    private Long proId;
    private Long empId;
    private Long sizeId;
    private String sizeCode;
    private String sizeName;
    private Long seqNo;
    private Integer dr;
}
