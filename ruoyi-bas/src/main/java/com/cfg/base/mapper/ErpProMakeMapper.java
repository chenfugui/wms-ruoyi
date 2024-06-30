package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProMake;

/**
 * 服装生产管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProMakeMapper extends BaseMapper<ErpProMake> {
    /**
     * 查询服装生产管理列表
     *
     * @param erpProMake 服装生产管理
     * @return 服装生产管理集合
     */
    List<ErpProMake> selectByEntity(ErpProMake erpProMake);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
