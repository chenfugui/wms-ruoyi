package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpAppVer;
import com.cfg.base.pojo.dto.ErpAppVerDTO;
import com.cfg.base.pojo.vo.ErpAppVerVO;
import java.util.List;
/**
 * App版本  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpAppVerConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpAppVerDTO do2dto(ErpAppVer source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpAppVer dto2do(ErpAppVerDTO source);

    List<ErpAppVerVO> dos2vos(List<ErpAppVer> list);
}
