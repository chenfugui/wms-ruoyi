package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProBatchExe;
import com.cfg.base.pojo.dto.ErpProBatchExeDTO;
import com.cfg.base.pojo.vo.ErpProBatchExeVO;
import java.util.List;
/**
 * 服装生产进度  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProBatchExeConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProBatchExeDTO do2dto(ErpProBatchExe source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProBatchExe dto2do(ErpProBatchExeDTO source);

    List<ErpProBatchExeVO> dos2vos(List<ErpProBatchExe> list);
}
