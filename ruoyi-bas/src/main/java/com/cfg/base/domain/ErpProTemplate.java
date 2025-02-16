package com.cfg.base.domain;

import java.util.List;
import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 模板表对象 erp_pro_template
 * 
 * @author chenfg
 */
@ApiModel(description="模板表对象")
@Data
@TableName("erp_pro_template")
public class ErpProTemplate extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("模板编码")
    @Excel(name = "模板编码")
    private String tmpCode;

    @ApiModelProperty("模板名称")
    @Excel(name = "模板名称")
    private String tmpName;

    @ApiModelProperty("模板类型")
    @Excel(name = "模板类型")
    private String tmpType;

    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String tmpMemo;

    @ApiModelProperty("删除标志")
    private Integer delFlag;

    @ApiModelProperty("单位ID")
    private Long empId;

}
