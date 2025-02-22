package com.cfg.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfg.base.domain.ErpAppRoleMenu;
import com.cfg.base.domain.ErpAppVer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ErpAppVerMapper extends BaseMapper<ErpAppVer> {
    /**
     * 查询App版本列表
     *
     * @param erpAppVer App版本
     * @return App版本集合
     */
    List<ErpAppVer> selectByEntity(ErpAppVer erpAppVer);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    /**
     * 根据发布编号查询最新版本
     * @return 最新版本信息
     */
    ErpAppVer selectByMaxReleaseNum();
}
