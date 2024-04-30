package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.Make;
import com.cfg.base.pojo.dto.MakeDTO;
import com.cfg.base.pojo.vo.MakeVO;
import java.util.List;
/**
 * 产品生产表  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface MakeConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    MakeDTO do2dto(Make source);

    /**
     * @param source DTO
     * @return DO
     */
    Make dto2do(MakeDTO source);

    List<MakeVO> dos2vos(List<Make> list);
}
