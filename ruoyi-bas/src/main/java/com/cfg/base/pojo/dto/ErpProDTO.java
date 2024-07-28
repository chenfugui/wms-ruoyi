package com.cfg.base.pojo.dto;

import com.cfg.base.domain.ErpProColor;
import com.cfg.base.domain.ErpProPrice;
import com.cfg.base.domain.ErpProProcess;
import com.cfg.base.domain.ErpProSize;
import com.ruoyi.common.core.domain.BaseAudit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 服装产品管理 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpProDTO extends BaseAudit {
    private Long proId;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proType;
    private String proMemo;
    private String status;
    private Long empId;
    private Long seqNo;
    private Integer delFlag;
    @ApiModelProperty("颜色列表")
    private List<ErpProColor> colorList;
    @ApiModelProperty("尺码列表")
    private List<ErpProSize> sizeList;
    @ApiModelProperty("工序列表")
    private List<ErpProProcess> procList;
    @ApiModelProperty("工价列表")
    private List<ErpProPrice> priceList;
}
