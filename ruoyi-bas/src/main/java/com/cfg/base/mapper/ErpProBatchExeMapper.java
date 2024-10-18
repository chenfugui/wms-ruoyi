package com.cfg.base.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cfg.base.domain.ErpProBatchExe;

/**
 * 服装生产进度Mapper接口
 * 
 * @author chenfg
 */
public interface ErpProBatchExeMapper extends BaseMapper<ErpProBatchExe> {
    /**
     * 查询服装生产进度列表
     *
     * @param erpProBatchExe 服装生产进度
     * @return 服装生产进度集合
     */
    List<ErpProBatchExe> selectByEntity(ErpProBatchExe erpProBatchExe);

    /**
     * 批量软删除
     * @param ids
     * @return
    */
    int updateDelFlagByIds(@Param("ids") Long[] ids);

    List<ErpProBatchExe> selectByProMaxkeId(Long proMakeId);
}
