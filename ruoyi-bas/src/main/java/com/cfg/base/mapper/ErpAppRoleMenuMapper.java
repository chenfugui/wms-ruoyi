package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpAppRoleMenu;

/**
 * 角色菜单表Mapper接口
 * 
 * @author chenfg
 */
public interface ErpAppRoleMenuMapper extends BaseMapper<ErpAppRoleMenu> {
    /**
     * 查询角色菜单表列表
     *
     * @param erpAppRoleMenu 角色菜单表
     * @return 角色菜单表集合
     */
    List<ErpAppRoleMenu> selectByEntity(ErpAppRoleMenu erpAppRoleMenu);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int deleteByIds(@Param("ids") Long[] ids);


   /** 
    * @description:
    * @author chenf
    * @date: 2024/8/4 21:52
    * @param: null 
    * @return:  
    */ 
    int deleteByRoleId(@Param("roleId") Long roleId);
}
