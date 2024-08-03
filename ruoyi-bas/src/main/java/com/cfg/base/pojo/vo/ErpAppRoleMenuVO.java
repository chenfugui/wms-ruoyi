package com.cfg.base.pojo.vo;

import com.ruoyi.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 角色菜单表 数据视图对象
 * 
 * @author chenfg
 */
@Data
public class ErpAppRoleMenuVO extends BaseAudit {
   /** 主键id */
    private Long id;
   /** 角色id */
    @Excel(name = "角色id")
    private String roleId;
   /** 菜单id */
    @Excel(name = "菜单id")
    private String menuId;
}
