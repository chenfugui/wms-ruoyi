package com.cfg.base.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.cfg.base.domain.ErpAppMenu;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author chenf
 * @version 1.0
 * @description: TODO
 * @date 2024/8/4 22:42
 */
@Data
public class AppMenuGroupDTO {
    private List<AppMenuDTO> menuGroupList;

    /** 
     * @description: 获取app分组功能菜单
     * @author chenf
     * @date: 2024/8/4 22:58
     * @param:  
     * @return:  
     */ 
    public static AppMenuGroupDTO buildMenuGroupDTO(List<ErpAppMenu> appMenuList) {
        AppMenuGroupDTO appMenuGroupDTO = new AppMenuGroupDTO();
        if(CollectionUtil.isNotEmpty(appMenuList)){
            Map<String,List<AppMenuItemDTO>> catalogMenuMap = new TreeMap<>();
            for(ErpAppMenu appMenu : appMenuList){
                AppMenuItemDTO menuItemDTO = convertToAppMenuItemDTO(appMenu);
                if(catalogMenuMap.containsKey(appMenu.getCatalogId())){
                    catalogMenuMap.get(appMenu.getCatalogId()).add(menuItemDTO);
                }else {
                    List<AppMenuItemDTO> menuList = new ArrayList<>();
                    menuList.add(menuItemDTO);
                    catalogMenuMap.put(appMenu.getCatalogId(), menuList);
                }
            }
            List<AppMenuDTO> menuList = new ArrayList<>();
            for (Map.Entry<String, List<AppMenuItemDTO>> Entry : catalogMenuMap.entrySet()) {
                AppMenuDTO appMenuDTO = new AppMenuDTO();
                List<AppMenuItemDTO> appMenuItemDTOS = Entry.getValue();
                if(CollectionUtil.isNotEmpty(appMenuItemDTOS)){
                    appMenuDTO.setHeader(appMenuItemDTOS.get(0).getGroupName());
                    appMenuDTO.setMenuList(appMenuItemDTOS);
                }
                menuList.add(appMenuDTO);
            }
            appMenuGroupDTO.setMenuGroupList(menuList);
        }
        return appMenuGroupDTO;
    }

    /** 
     * @description: 转换成AppMenuItemDTO
     * @author chenf
     * @date: 2024/8/4 23:00
     * @param:  
     * @return:  
     */ 
    public static AppMenuItemDTO convertToAppMenuItemDTO(ErpAppMenu erpAppMenu){
        AppMenuItemDTO menuItemDTO = new AppMenuItemDTO();
        if(null!=erpAppMenu){
            menuItemDTO.setIcon(erpAppMenu.getIcon());
            menuItemDTO.setName(erpAppMenu.getMenuName());
            menuItemDTO.setPath(erpAppMenu.getMenuPath());
            menuItemDTO.setGroupName(erpAppMenu.getCatalogName());
        }
        return menuItemDTO;
    }
}
