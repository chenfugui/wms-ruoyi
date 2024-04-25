package com.cfg.idgen.dao;

import com.cfg.idgen.entity.BasCommonIdDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
* @author chenfg
* @description 针对表【FICS_COMMON_ID(公共ID信息表)】的数据库操作Mapper
* @createDate 2023-04-17 16:46:30
* @Entity cn.hsa.ics.ext.idgen.entity.FicsCommonIdDO
*/
public interface BasCommonIdDAO {

    int deleteByPrimaryKey(Long id);

    int insert(BasCommonIdDO record);

    int insertSelective(BasCommonIdDO record);

    BasCommonIdDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasCommonIdDO record);

    int updateByPrimaryKey(BasCommonIdDO record);

    /**
     * @Description 更新段信息
     * @author chenfg
     * @Date 2023/4/19 15:04
     * @param id 记录id
     * @param version 版本
     * @param updtTime 更新时间
     * @return int
     **/
    int updateMaxId(@Param("id") Long id, @Param("version") Long version,@Param("updtTime") Timestamp updtTime);

    /**
     * @Description 更新段信息
     * @author chenfg
     * @Date 2023/4/20 15:04
     * @param id 记录id
     * @param version 版本
     * @param updtTime 更新时间
     * @return int
     **/
    int updateMaxIdWithMaxId(@Param("id") Long id,@Param("maxId") Long maxId, @Param("version") Long version,@Param("updtTime") Timestamp updtTime);

    /**
     * @Description 查询段信息
     * @author chenfg
     * @Date 2023/4/19 15:22
     * @param bizType 业务类型
     * @param idType id类型
     * @param segFlag 段标识
     * @return cn.hsa.ics.ext.idgen.entity.FicsCommonIdDO
     **/
    BasCommonIdDO selectSegment(@Param("bizType") String bizType, @Param("idType") String idType,@Param("segFlag") String segFlag);


    /**
     * @Description 根据id列表查询segment
     * @author chenfg
     * @Date 2023/4/20 16:01
     * @param idList id列表
     * @return cn.hsa.ics.ext.idgen.entity.FicsCommonIdDO
     **/
    List<BasCommonIdDO> selectSegmentByIdList(@Param("idList") List<Long> idList);

    /**
     * @Description 查询段信息
     * @author chenfg
     * @Date 2023/4/19 15:22
     * @param bizType 业务类型
     * @param idType id类型
     * @param segFlag 段标识
     * @return cn.hsa.ics.ext.idgen.entity.FicsCommonIdDO
     **/
    List<BasCommonIdDO> selectSegSelective(@Param("bizType") String bizType, @Param("idType") String idType,@Param("segFlag") String segFlag);

}
