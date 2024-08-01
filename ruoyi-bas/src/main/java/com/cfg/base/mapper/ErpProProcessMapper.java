package com.cfg.base.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProProcess;

/**
 * 服装工序信息Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProProcessMapper extends BaseMapper<ErpProProcess> {
    /**
     * 查询服装工序信息列表
     *
     * @param erpProProcess 服装工序信息
     * @return 服装工序信息集合
     */
    List<ErpProProcess> selectByEntity(ErpProProcess erpProProcess);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);


    /***
     * @author chenfg
     * @date: 2024/8/1 20:58
     * @description:  查询工序列表
     * @param queryWrapper
     * @return: java.util.List<java.lang.String>
     */
    List<String> selectProcessNameList(@Param("ew") Wrapper<String> queryWrapper);
}
