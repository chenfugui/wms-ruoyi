package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.common.core.domain.entity.CommonEmp;

/**
 * 单位信息Mapper接口
 * 
 * @author chenfg
 */
public interface CommonEmpMapper extends BaseMapper<CommonEmp> {
    /**
     * 查询单位信息列表
     *
     * @param commonEmp 单位信息
     * @return 单位信息集合
     */
    List<CommonEmp> selectByEntity(CommonEmp commonEmp);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);


    /**
     * 新增单位
     * @param commonEmp
     * @return
     */
    int insertEmp(CommonEmp commonEmp);
}
