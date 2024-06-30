package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpSize;
import com.cfg.base.pojo.dto.ErpSizeDTO;
import com.cfg.base.pojo.vo.ErpSizeVO;
import java.util.List;
/**
 * 服装尺码管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpSizeConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpSizeDTO do2dto(ErpSize source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpSize dto2do(ErpSizeDTO source);

    List<ErpSizeVO> dos2vos(List<ErpSize> list);
}
