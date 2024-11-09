package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpEmpDic;
import com.cfg.base.pojo.dto.ErpEmpDicDTO;
import com.cfg.base.pojo.vo.ErpEmpDicVO;
import java.util.List;
/**
 * 常用字典管理  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpEmpDicConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpEmpDicDTO do2dto(ErpEmpDic source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpEmpDic dto2do(ErpEmpDicDTO source);

    List<ErpEmpDicVO> dos2vos(List<ErpEmpDic> list);
}
