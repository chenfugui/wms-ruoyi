package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpPro;
import com.cfg.base.pojo.dto.ErpProDTO;
import com.cfg.base.pojo.vo.ErpProVO;
import java.util.List;
/**
 * 服装产品管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProDTO do2dto(ErpPro source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpPro dto2do(ErpProDTO source);

    List<ErpProVO> dos2vos(List<ErpPro> list);
}
