package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProTemplate;
import com.cfg.base.pojo.dto.ErpProTemplateDTO;
import com.cfg.base.pojo.vo.ErpProTemplateVO;
import java.util.List;
/**
 * 模板表  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring", uses = ErpProTemplateDetailConvert.class)
public interface ErpProTemplateConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProTemplateDTO do2dto(ErpProTemplate source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProTemplate dto2do(ErpProTemplateDTO source);

    List<ErpProTemplateVO> dos2vos(List<ErpProTemplate> list);
}
