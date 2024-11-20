package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfg.base.dto.ProMakePrintDTO;
import com.cfg.base.pojo.query.ErpProMakeQuery;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProMake;

/**
 * 服装生产管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProMakeMapper extends BaseMapper<ErpProMake> {
    /**
     * 查询服装生产管理列表
     *
     * @param erpProMake 服装生产管理
     * @return 服装生产管理集合
     */
    List<ErpProMake> selectByEntity(ErpProMake erpProMake);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);


    /***
     * @author chenfg
     * @date: 2024/11/20 13:12
     * @description:  根据产品id查询生产记录
     * @param makeQuery
     * @return: java.util.List<com.cfg.base.dto.ProMakePrintDTO>
     */
    List<ProMakePrintDTO> selectMakeListByProId(ErpProMakeQuery makeQuery);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteProMakeByIds(@Param("ids") List<Long>  ids);
}
