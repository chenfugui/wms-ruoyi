package com.cfg.base.domain;

import com.ruoyi.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 角色菜单表对象 erp_app_role_menu
 * 
 * @author chenfg
 */
@ApiModel(description="角色菜单表对象")
@Data
@TableName("erp_app_role_menu")
public class ErpAppRoleMenu extends BaseAudit {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("角色id")
    @Excel(name = "角色id")
    private String roleId;

    @ApiModelProperty("菜单id")
    @Excel(name = "菜单id")
    private String menuId;

}
