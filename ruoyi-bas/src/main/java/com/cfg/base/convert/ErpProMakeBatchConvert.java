package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProMakeBatch;
import com.cfg.base.pojo.dto.ErpProMakeBatchDTO;
import com.cfg.base.pojo.vo.ErpProMakeBatchVO;
import java.util.List;
/**
 * 服装生产批次  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProMakeBatchConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProMakeBatchDTO do2dto(ErpProMakeBatch source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProMakeBatch dto2do(ErpProMakeBatchDTO source);

    List<ErpProMakeBatchVO> dos2vos(List<ErpProMakeBatch> list);
}
