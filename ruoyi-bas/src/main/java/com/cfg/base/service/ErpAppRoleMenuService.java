package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.dto.AppRoleMenuDTO;
import com.cfg.idgen.service.IdGenService;
import com.cfg.idgen.service.impl.IdGenServiceImpl;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpAppRoleMenuMapper;
import com.cfg.base.domain.ErpAppRoleMenu;
import com.cfg.base.pojo.query.ErpAppRoleMenuQuery;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色菜单表Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpAppRoleMenuService {
    @Autowired
    private ErpAppRoleMenuMapper erpAppRoleMenuMapper;
    @Autowired
    private IdGenService idGenService;

    /**
     * 查询角色菜单表
     *
     * @param id 角色菜单表主键
     * @return 角色菜单表
     */
    public ErpAppRoleMenu selectById(Long id) {
        return erpAppRoleMenuMapper.selectById(id);
    }

    /**
     * 查询角色菜单表列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return 角色菜单表
     */
    public List<ErpAppRoleMenu> selectList(ErpAppRoleMenuQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpAppRoleMenu> qw = new QueryWrapper<>();
        String roleId = query.getRoleId();
        if (!StringUtils.isEmpty(roleId)) {
            qw.eq("role_id", roleId);
        }
        String menuId = query.getMenuId();
        if (!StringUtils.isEmpty(menuId)) {
            qw.eq("menu_id", menuId);
        }
        return erpAppRoleMenuMapper.selectList(qw);
    }

    /**
     * 新增角色菜单表
     *
     * @param erpAppRoleMenu 角色菜单表
     * @return 结果
     */
    public int insert(ErpAppRoleMenu erpAppRoleMenu) {
        erpAppRoleMenu.setCreateTime(LocalDateTime.now());
        OperatorUtils.setCreateInfo(erpAppRoleMenu);
        return erpAppRoleMenuMapper.insert(erpAppRoleMenu);
    }

  /**
   * @description:
   * @author chenf
   * @date: 2024/8/4 20:39
   * @param: roleMenuDTO
   * @return: int
   */
  @Transactional(rollbackFor = Exception.class)
    public int insertAll(AppRoleMenuDTO roleMenuDTO) {
        erpAppRoleMenuMapper.deleteByRoleId(roleMenuDTO.getRoleId());
        List<Long> menuIds = roleMenuDTO.getMenuIdList();
        if(CollectionUtils.isNotEmpty(menuIds)){
            for (Long menuId : menuIds) {
                ErpAppRoleMenu appRoleMenu = new ErpAppRoleMenu();
                appRoleMenu.setRoleId(roleMenuDTO.getRoleId());
                appRoleMenu.setMenuId(menuId);
                OperatorUtils.setCreateInfo(appRoleMenu);
                erpAppRoleMenuMapper.insert(appRoleMenu);
            }
        }
        return menuIds.size();
    }

    /**
     * 修改角色菜单表
     *
     * @param erpAppRoleMenu 角色菜单表
     * @return 结果
     */
    public int update(ErpAppRoleMenu erpAppRoleMenu) {
        OperatorUtils.setUpdateInfo(erpAppRoleMenu);
        return erpAppRoleMenuMapper.updateById(erpAppRoleMenu);
    }

    /**
     * 批量删除角色菜单表
     *
     * @param ids 需要删除的角色菜单表主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpAppRoleMenuMapper.deleteByIds(ids);
    }

    /**
     * 删除角色菜单表信息
     *
     * @param id 角色菜单表主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpAppRoleMenuMapper.deleteByIds(ids);
    }
}
