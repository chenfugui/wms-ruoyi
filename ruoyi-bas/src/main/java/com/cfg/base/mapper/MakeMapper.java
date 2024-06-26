package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.Make;

/**
 * 产品生产表Mapper接口
 * 
 * @author chenfg
 */
public interface MakeMapper extends BaseMapper<Make> {
    /**
     * 查询产品生产表列表
     *
     * @param make 产品生产表
     * @return 产品生产表集合
     */
    List<Make> selectByEntity(Make make);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
