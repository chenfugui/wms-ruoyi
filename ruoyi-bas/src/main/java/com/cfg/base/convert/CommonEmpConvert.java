package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.ruoyi.common.core.domain.entity.CommonEmp;
import com.cfg.base.pojo.dto.CommonEmpDTO;
import com.cfg.base.pojo.vo.CommonEmpVO;
import java.util.List;
/**
 * 单位信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface CommonEmpConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    CommonEmpDTO do2dto(CommonEmp source);

    /**
     * @param source DTO
     * @return DO
     */
    CommonEmp dto2do(CommonEmpDTO source);

    List<CommonEmpVO> dos2vos(List<CommonEmp> list);
}
