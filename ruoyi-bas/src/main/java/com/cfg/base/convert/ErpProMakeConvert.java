package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProMake;
import com.cfg.base.pojo.dto.ErpProMakeDTO;
import com.cfg.base.pojo.vo.ErpProMakeVO;
import java.util.List;
/**
 * 服装生产管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProMakeConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProMakeDTO do2dto(ErpProMake source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProMake dto2do(ErpProMakeDTO source);

    List<ErpProMakeVO> dos2vos(List<ErpProMake> list);
}
