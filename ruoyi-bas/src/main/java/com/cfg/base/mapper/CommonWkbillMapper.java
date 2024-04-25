package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.CommonWkbill;

/**
 * 工单信息Mapper接口
 * 
 * @author chenfg
 */
public interface CommonWkbillMapper extends BaseMapper<CommonWkbill> {
    /**
     * 查询工单信息列表
     *
     * @param commonWkbill 工单信息
     * @return 工单信息集合
     */
    List<CommonWkbill> selectByEntity(CommonWkbill commonWkbill);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);
}
