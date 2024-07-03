package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProSize;
import com.cfg.base.pojo.dto.ErpProSizeDTO;
import com.cfg.base.pojo.vo.ErpProSizeVO;
import java.util.List;
/**
 * 服装生产尺码  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProSizeConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProSizeDTO do2dto(ErpProSize source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProSize dto2do(ErpProSizeDTO source);

    List<ErpProSizeVO> dos2vos(List<ErpProSize> list);
}
