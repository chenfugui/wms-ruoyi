package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 常用字典管理对象 erp_emp_dic
 * 
 * @author chenfg
 */
@ApiModel(description="常用字典管理对象")
@Data
@TableName("erp_emp_dic")
public class ErpEmpDic extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("类型编码")
    @Excel(name = "类型编码")
    private String typeCode;

    @ApiModelProperty("类型名称")
    @Excel(name = "类型名称")
    private String typeName;

    @ApiModelProperty("字典项编码")
    @Excel(name = "字典项编码")
    private String itemCode;

    @ApiModelProperty("字典项名称")
    @Excel(name = "字典项名称")
    private String itemName;

    @ApiModelProperty("字典项值")
    @Excel(name = "字典项值")
    private String itemValue;

    @ApiModelProperty("单位id")
    @Excel(name = "单位id")
    private Long empId;

}
