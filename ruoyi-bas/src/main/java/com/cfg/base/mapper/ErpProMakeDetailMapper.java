package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProMakeDetail;

/**
 * 服装生产明细Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProMakeDetailMapper extends BaseMapper<ErpProMakeDetail> {
    /**
     * 查询服装生产明细列表
     *
     * @param erpProMakeDetail 服装生产明细
     * @return 服装生产明细集合
     */
    List<ErpProMakeDetail> selectByEntity(ErpProMakeDetail erpProMakeDetail);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
