package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpColor;
import com.cfg.base.pojo.dto.ErpColorDTO;
import com.cfg.base.pojo.vo.ErpColorVO;
import java.util.List;
/**
 * 服装颜色管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpColorConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpColorDTO do2dto(ErpColor source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpColor dto2do(ErpColorDTO source);

    List<ErpColorVO> dos2vos(List<ErpColor> list);
}
