package com.cfg.base.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chenf
 * @version 1.0
 * @description: 角色功能菜单DTO
 * @date 2024/8/4 19:32
 */
@Data
public class AppRoleMenuDTO {

    private Long roleId;
    private List<Long> menuIdList;
}
