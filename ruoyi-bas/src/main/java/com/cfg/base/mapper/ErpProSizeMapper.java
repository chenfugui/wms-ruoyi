package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProSize;

/**
 * 服装生产尺码Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProSizeMapper extends BaseMapper<ErpProSize> {
    /**
     * 查询服装生产尺码列表
     *
     * @param erpProSize 服装生产尺码
     * @return 服装生产尺码集合
     */
    List<ErpProSize> selectByEntity(ErpProSize erpProSize);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
