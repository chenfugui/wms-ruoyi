package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProProcess;
import com.cfg.base.pojo.dto.ErpProProcessDTO;
import com.cfg.base.pojo.vo.ErpProProcessVO;
import java.util.List;
/**
 * 服装工序信息  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProProcessConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProProcessDTO do2dto(ErpProProcess source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProProcess dto2do(ErpProProcessDTO source);

    List<ErpProProcessVO> dos2vos(List<ErpProProcess> list);
}
