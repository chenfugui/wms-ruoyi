package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProColor;

/**
 * 产品颜色管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProColorMapper extends BaseMapper<ErpProColor> {
    /**
     * 查询产品颜色管理列表
     *
     * @param erpProColor 产品颜色管理
     * @return 产品颜色管理集合
     */
    List<ErpProColor> selectByEntity(ErpProColor erpProColor);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    void deleteByProIds(@Param("ids") List<Long> ids);
}
