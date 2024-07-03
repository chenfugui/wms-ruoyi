package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProMakeBatch;

/**
 * 服装生产批次Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProMakeBatchMapper extends BaseMapper<ErpProMakeBatch> {
    /**
     * 查询服装生产批次列表
     *
     * @param erpProMakeBatch 服装生产批次
     * @return 服装生产批次集合
     */
    List<ErpProMakeBatch> selectByEntity(ErpProMakeBatch erpProMakeBatch);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
