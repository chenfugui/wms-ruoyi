package com.cfg.base.pojo.dto;

import com.ruoyi.common.core.domain.BaseAudit;
import lombok.Data;
/**
 * 角色菜单表 DTO 对象
 *
 * @author chenfg
 */
@Data
public class ErpAppRoleMenuDTO extends BaseAudit {
    private Long id;
    private String roleId;
    private String menuId;
}
