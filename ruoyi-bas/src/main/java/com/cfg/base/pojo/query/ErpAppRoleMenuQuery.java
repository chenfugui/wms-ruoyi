package com.cfg.base.pojo.query;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色菜单表 查询 对象
 *
 * @author chenfg
 */
@ApiModel(description="角色菜单表 查询 对象")
@Data
public class ErpAppRoleMenuQuery {
    @ApiModelProperty("角色id 精确匹配")
    private String roleId;

    @ApiModelProperty("菜单id 精确匹配")
    private String menuId;

}
