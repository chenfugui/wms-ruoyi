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

    /**
     * @author chenfg
     * @date: 2024/11/20 16:54
     * @description:  根据生产生产id查询扫菲记录
     * @param proMakeId 生产id
     * @return: java.util.List<com.cfg.base.domain.ErpProBatchExe>
     */
    List<ErpProBatchExe> selectByProMakeId(@Param("proMakeId") Long proMakeId);

    /**
     * @author chenfg
     * @date: 2024/11/20 16:55
     * @description:  根据生产生产id集合查询扫菲记录
     * @param proMakeIds
     * @return: java.util.List<com.cfg.base.domain.ErpProBatchExe>
     */
    List<ErpProBatchExe> selectByProMakeIds(@Param("proMakeIds") List<Long> proMakeIds);


}
