package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.CommonStep;

/**
 * 工序信息Mapper接口
 * 
 * @author chenfg
 */
public interface CommonStepMapper extends BaseMapper<CommonStep> {
    /**
     * 查询工序信息列表
     *
     * @param commonStep 工序信息
     * @return 工序信息集合
     */
    List<CommonStep> selectByEntity(CommonStep commonStep);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
