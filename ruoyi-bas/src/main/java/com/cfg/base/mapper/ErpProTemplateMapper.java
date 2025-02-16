package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProTemplate;

/**
 * 模板表Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProTemplateMapper extends BaseMapper<ErpProTemplate> {
    /**
     * 查询模板表列表
     *
     * @param erpProTemplate 模板表
     * @return 模板表集合
     */
    List<ErpProTemplate> selectByEntity(ErpProTemplate erpProTemplate);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

}
