package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProc;
import com.cfg.base.pojo.dto.ErpProcDTO;
import com.cfg.base.pojo.vo.ErpProcVO;
import java.util.List;
/**
 * 服装工序管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProcConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProcDTO do2dto(ErpProc source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProc dto2do(ErpProcDTO source);

    List<ErpProcVO> dos2vos(List<ErpProc> list);
}
