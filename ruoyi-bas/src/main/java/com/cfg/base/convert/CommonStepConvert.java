package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.CommonStep;
import com.cfg.base.pojo.dto.CommonStepDTO;
import com.cfg.base.pojo.vo.CommonStepVO;
import java.util.List;
/**
 * 工序信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface CommonStepConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    CommonStepDTO do2dto(CommonStep source);

    /**
     * @param source DTO
     * @return DO
     */
    CommonStep dto2do(CommonStepDTO source);

    List<CommonStepVO> dos2vos(List<CommonStep> list);
}
