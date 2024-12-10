package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产批次 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProMakeBatchDTO extends BaseAudit {
    private Long id;
    private Long proMakeId;
    private String proMakeNo;
    private Long proId;
    private Long sizeId;
    private String batchNo;
    private String sizeName;
    private String sizeCode;
    private Long colorId;
    private String colorCode;
    private String colorName;
    private Long makeNum;
    private String bedNo;
    private Long pkgStartNo;
    private Long pkgEndNo;
    private Long empId;
    private Integer delFlag;
    private Long seqNo;
    private Long stepId;
    private String stepName;
    private String stepCode;
    private Long completeNum;
    // 登记数量
    private Long regNum;
}
