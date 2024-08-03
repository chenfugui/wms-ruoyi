package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpAppMenu;
import com.cfg.base.pojo.dto.ErpAppMenuDTO;
import com.cfg.base.pojo.vo.ErpAppMenuVO;
import java.util.List;
/**
 * app功能菜单  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpAppMenuConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpAppMenuDTO do2dto(ErpAppMenu source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpAppMenu dto2do(ErpAppMenuDTO source);

    List<ErpAppMenuVO> dos2vos(List<ErpAppMenu> list);
}
