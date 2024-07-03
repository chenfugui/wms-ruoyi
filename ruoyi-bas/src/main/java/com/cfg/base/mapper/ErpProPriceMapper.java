package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProPrice;

/**
 * 服装工价信息Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProPriceMapper extends BaseMapper<ErpProPrice> {
    /**
     * 查询服装工价信息列表
     *
     * @param erpProPrice 服装工价信息
     * @return 服装工价信息集合
     */
    List<ErpProPrice> selectByEntity(ErpProPrice erpProPrice);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
