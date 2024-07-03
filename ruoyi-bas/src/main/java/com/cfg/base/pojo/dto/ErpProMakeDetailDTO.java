package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 服装生产明细 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProMakeDetailDTO extends BaseAudit {
    private Long id;
    private Long proMakeId;
    private String proMakeNo;
    private Long proId;
    private Long sizeId;
    private Long colorId;
    private Long clothId;
    private String clothCode;
    private String clothName;
    private Long makeNum;
    private Integer delFlag;
    private Long empId;
}
