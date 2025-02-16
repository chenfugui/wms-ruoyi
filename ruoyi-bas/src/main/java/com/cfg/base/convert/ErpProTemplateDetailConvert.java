package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpProTemplateDetail;
import com.cfg.base.pojo.dto.ErpProTemplateDetailDTO;
import com.cfg.base.pojo.vo.ErpProTemplateDetailVO;
import java.util.List;
/**
 * 模板项目明细管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpProTemplateDetailConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpProTemplateDetailDTO do2dto(ErpProTemplateDetail source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpProTemplateDetail dto2do(ErpProTemplateDetailDTO source);

    List<ErpProTemplateDetailVO> dos2vos(List<ErpProTemplateDetail> list);
}
