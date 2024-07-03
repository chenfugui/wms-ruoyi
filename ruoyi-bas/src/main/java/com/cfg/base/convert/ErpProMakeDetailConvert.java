package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProMakeDetail;
import com.cfg.base.pojo.dto.ErpProMakeDetailDTO;
import com.cfg.base.pojo.vo.ErpProMakeDetailVO;
import java.util.List;
/**
 * 服装生产明细  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProMakeDetailConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProMakeDetailDTO do2dto(ErpProMakeDetail source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProMakeDetail dto2do(ErpProMakeDetailDTO source);

    List<ErpProMakeDetailVO> dos2vos(List<ErpProMakeDetail> list);
}
