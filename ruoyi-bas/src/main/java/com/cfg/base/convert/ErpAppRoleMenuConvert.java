package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.ErpAppRoleMenu;
import com.cfg.base.pojo.dto.ErpAppRoleMenuDTO;
import com.cfg.base.pojo.vo.ErpAppRoleMenuVO;
import java.util.List;
/**
 * 角色菜单表  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ErpAppRoleMenuConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ErpAppRoleMenuDTO do2dto(ErpAppRoleMenu source);

    /**
     * @param source DTO
     * @return DO
     */
    ErpAppRoleMenu dto2do(ErpAppRoleMenuDTO source);

    List<ErpAppRoleMenuVO> dos2vos(List<ErpAppRoleMenu> list);
}
