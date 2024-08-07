package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpAppMenu;

/**
 * app功能菜单Mapper接口
 * 
 * @author chenfg
 */
public interface ErpAppMenuMapper extends BaseMapper<ErpAppMenu> {
    /**
     * 查询app功能菜单列表
     *
     * @param erpAppMenu app功能菜单
     * @return app功能菜单集合
     */
    List<ErpAppMenu> selectByEntity(ErpAppMenu erpAppMenu);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    /**
     * 根据角色id查询菜单
     * @param roleId 角色id
     * @return
     */
    List<ErpAppMenu> selectByRoleId(@Param("roleId") Long roleId);

    /** 
     * @description: 根据角色查询菜单
     * @author chenf
     * @date: 2024/8/4 23:07
     * @param:  
     * @return:  
     */ 
    List<ErpAppMenu> selectByRoleIdList(@Param("roleIds") List<Long> roleIds);
}
