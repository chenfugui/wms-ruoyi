package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProc;

/**
 * 服装工序管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProcMapper extends BaseMapper<ErpProc> {
    /**
     * 查询服装工序管理列表
     *
     * @param erpProc 服装工序管理
     * @return 服装工序管理集合
     */
    List<ErpProc> selectByEntity(ErpProc erpProc);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
