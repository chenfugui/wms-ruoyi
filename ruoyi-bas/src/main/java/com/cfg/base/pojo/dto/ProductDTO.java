package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 产品信息表 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ProductDTO extends BaseAudit {
    private Long proId;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proType;
    private String proMemo;
    private String status;
    private Long empId;
}
