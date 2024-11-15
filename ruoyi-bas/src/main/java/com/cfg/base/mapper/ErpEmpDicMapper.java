package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpEmpDic;

/**
 * 常用字典管理Mapper接口
 * 
 * @author chenfg
 */
public interface ErpEmpDicMapper extends BaseMapper<ErpEmpDic> {
    /**
     * 查询常用字典管理列表
     *
     * @param erpEmpDic 常用字典管理
     * @return 常用字典管理集合
     */
    List<ErpEmpDic> selectByEntity(ErpEmpDic erpEmpDic);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Integer[] ids);

    /***
     * @author chenfg
     * @date: 2024/11/15 14:46
     * @description:  删除字典项
     * @param itemNames
     * @param typeCode
     * @param empId
     * @return: void
     */
    int deleteByItemNameList(@Param("itemNames") List<String> itemNames,@Param("typeCode") String typeCode,@Param("empId") Integer empId);
}
