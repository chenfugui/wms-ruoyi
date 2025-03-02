package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProTemplateDetail;

/**
 * 模板项目明细管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProTemplateDetailMapper extends BaseMapper<ErpProTemplateDetail> {
    /**
     * 查询模板项目明细管理列表
     *
     * @param erpProTemplateDetail 模板项目明细管理
     * @return 模板项目明细管理集合
     */
    List<ErpProTemplateDetail> selectByEntity(ErpProTemplateDetail erpProTemplateDetail);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    /**
     * 批量删除模板表
     * @param tmpId 根据模板ID删除模板明细
     * @return 结果
     */
    void deleteErpProTemplateDetailByTmpId(@Param("tmpId") Long tmpId);

    /**
     * 批量删除模板表
     * @param ids 根据模板ID删除模板明细
     * @return 结果
     */
    void deleteByTmpIds(@Param("ids") Long[] ids);

}
