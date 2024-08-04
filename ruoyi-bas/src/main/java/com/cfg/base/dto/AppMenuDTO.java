package com.cfg.base.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chenf
 * @version 1.0
 * @description: TODO
 * @date 2024/8/4 22:40
 */
@Data
public class AppMenuDTO {
    private String header;
    private List<AppMenuItemDTO> menuList;
}
