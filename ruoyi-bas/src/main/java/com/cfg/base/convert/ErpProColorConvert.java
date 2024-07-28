package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProColor;
import com.cfg.base.pojo.dto.ErpProColorDTO;
import com.cfg.base.pojo.vo.ErpProColorVO;
import java.util.List;
/**
 * 产品颜色管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProColorConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProColorDTO do2dto(ErpProColor source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProColor dto2do(ErpProColorDTO source);

    List<ErpProColorVO> dos2vos(List<ErpProColor> list);
}
