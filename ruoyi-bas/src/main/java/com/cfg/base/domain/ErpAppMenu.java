package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * app功能菜单对象 erp_app_menu
 * 
 * @author chenfg
 */
@ApiModel(description="app功能菜单对象")
@Data
@TableName("erp_app_menu")
public class ErpAppMenu extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("目录id")
    private String catalogId;

    @ApiModelProperty("目录名称")
    @Excel(name = "目录名称")
    private String catalogName;

    @ApiModelProperty("菜单编码")
    @Excel(name = "菜单编码")
    private String menuCode;

    @ApiModelProperty("菜单名称")
    @Excel(name = "菜单名称")
    private String menuName;

    @ApiModelProperty("菜单路径")
    @Excel(name = "菜单路径")
    private String menuPath;

    @ApiModelProperty("序号")
    private Integer seqNo;

    @ApiModelProperty("删除标志")
    private Integer delFlag;

}
