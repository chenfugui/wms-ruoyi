package com.cfg.base.service;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cfg.base.dto.AppMenuGroupDTO;
import com.cfg.idgen.util.OperatorUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.cfg.base.mapper.ErpAppMenuMapper;
import com.cfg.base.domain.ErpAppMenu;
import com.cfg.base.pojo.query.ErpAppMenuQuery;

/**
 * app功能菜单Service业务层处理
 *
 *
 * @author chenfg
 */
@Service
public class ErpAppMenuService {
    @Autowired
    private ErpAppMenuMapper erpAppMenuMapper;

    /**
     * 查询app功能菜单
     *
     * @param id app功能菜单主键
     * @return app功能菜单
     */
    public ErpAppMenu selectById(Long id) {
        return erpAppMenuMapper.selectById(id);
    }

    /**
     * 查询app功能菜单列表
     *
     * @param query 查询条件
     * @param page 分页条件
     * @return app功能菜单
     */
    public List<ErpAppMenu> selectList(ErpAppMenuQuery query, Pageable page) {
        if (page != null) {
            PageHelper.startPage(page.getPageNumber() + 1, page.getPageSize());
        }
        QueryWrapper<ErpAppMenu> qw = new QueryWrapper<>();
        qw.eq("del_flag",0);
        String catalogNameLike = query.getCatalogNameLike();
        if (!StringUtils.isEmpty(catalogNameLike)) {
            qw.like("catalog_name", catalogNameLike);
        }
        String menuCode = query.getMenuCode();
        if (!StringUtils.isEmpty(menuCode)) {
            qw.eq("menu_code", menuCode);
        }
        String menuNameLike = query.getMenuNameLike();
        if (!StringUtils.isEmpty(menuNameLike)) {
            qw.like("menu_name", menuNameLike);
        }
        String menuPath = query.getMenuPath();
        if (!StringUtils.isEmpty(menuPath)) {
            qw.eq("menu_path", menuPath);
        }
        qw.orderByAsc("catalog_id","id");
        return erpAppMenuMapper.selectList(qw);
    }

    /**
     * 根据角色id查询角色菜单
     * @param roleId
     * @return
     */
    public List<ErpAppMenu> selectListByRoleId(Long roleId){
        return erpAppMenuMapper.selectByRoleId(roleId);
    }

    /**
     * 根据角色id查询角色菜单
     * @param roleIds
     * @return
     */
    public AppMenuGroupDTO selectListByRoleIdList(List<Long> roleIds){
        List<ErpAppMenu> menuList =  erpAppMenuMapper.selectByRoleIdList(roleIds);
        return AppMenuGroupDTO.buildMenuGroupDTO(menuList);
    }

    /**
     * 新增app功能菜单
     *
     * @param erpAppMenu app功能菜单
     * @return 结果
     */
    public int insert(ErpAppMenu erpAppMenu) {
        erpAppMenu.setDelFlag(0);
        erpAppMenu.setCreateTime(LocalDateTime.now());
        OperatorUtils.setCreateInfo(erpAppMenu);
        return erpAppMenuMapper.insert(erpAppMenu);
    }

    /**
     * 修改app功能菜单
     *
     * @param erpAppMenu app功能菜单
     * @return 结果
     */
    public int update(ErpAppMenu erpAppMenu) {
        OperatorUtils.setUpdateInfo(erpAppMenu);
        return erpAppMenuMapper.updateById(erpAppMenu);
    }

    /**
     * 批量删除app功能菜单
     *
     * @param ids 需要删除的app功能菜单主键
     * @return 结果
     */
    public int deleteByIds(Long[] ids) {
        return erpAppMenuMapper.updateDelFlagByIds(ids);
    }

    /**
     * 删除app功能菜单信息
     *
     * @param id app功能菜单主键
     * @return 结果
     */
    public int deleteById(Long id) {
        Long[] ids = {id};
        return erpAppMenuMapper.updateDelFlagByIds(ids);
    }
}
