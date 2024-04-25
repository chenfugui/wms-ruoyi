package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.CommonWkbill;
import com.cfg.base.pojo.dto.CommonWkbillDTO;
import com.cfg.base.pojo.vo.CommonWkbillVO;
import java.util.List;
/**
 * 工单信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface CommonWkbillConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    CommonWkbillDTO do2dto(CommonWkbill source);

    /**
     * @param source DTO
     * @return DO
     */
    CommonWkbill dto2do(CommonWkbillDTO source);

    List<CommonWkbillVO> dos2vos(List<CommonWkbill> list);
}
