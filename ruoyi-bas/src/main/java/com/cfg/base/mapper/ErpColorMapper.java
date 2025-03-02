package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpColor;
import org.springframework.stereotype.Component;

/**
 * 服装颜色管理Mapper接口
 * 
 * @author chenfg
 */
@Component
public interface ErpColorMapper extends BaseMapper<ErpColor> {
    /**
     * 查询服装颜色管理列表
     *
     * @param erpColor 服装颜色管理
     * @return 服装颜色管理集合
     */
    List<ErpColor> selectByEntity(ErpColor erpColor);

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
    void deleteByProIds(@Param("ids") Long[] ids);
}
