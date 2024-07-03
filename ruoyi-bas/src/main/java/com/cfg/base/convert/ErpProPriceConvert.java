package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProPrice;
import com.cfg.base.pojo.dto.ErpProPriceDTO;
import com.cfg.base.pojo.vo.ErpProPriceVO;
import java.util.List;
/**
 * 服装工价信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProPriceConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProPriceDTO do2dto(ErpProPrice source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProPrice dto2do(ErpProPriceDTO source);

    List<ErpProPriceVO> dos2vos(List<ErpProPrice> list);
}
