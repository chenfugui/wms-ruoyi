package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpSize;

/**
 * 服装尺码管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpSizeMapper extends BaseMapper<ErpSize> {
    /**
     * 查询服装尺码管理列表
     *
     * @param erpSize 服装尺码管理
     * @return 服装尺码管理集合
     */
    List<ErpSize> selectByEntity(ErpSize erpSize);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
