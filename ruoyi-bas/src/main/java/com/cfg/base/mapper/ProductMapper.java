package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.Product;

/**
 * 产品信息表Mapper接口
 * 
 * @author chenfg
 */
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 查询产品信息表列表
     *
     * @param product 产品信息表
     * @return 产品信息表集合
     */
    List<Product> selectByEntity(Product product);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
