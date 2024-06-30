package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpPro;

/**
 * 服装产品管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProMapper extends BaseMapper<ErpPro> {
    /**
     * 查询服装产品管理列表
     *
     * @param erpPro 服装产品管理
     * @return 服装产品管理集合
     */
    List<ErpPro> selectByEntity(ErpPro erpPro);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
