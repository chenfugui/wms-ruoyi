package com.cyl.wms.convert;

import com.cyl.wms.domain.entity.InventorySettlement;
import org.mapstruct.Mapper;
import com.cyl.wms.pojo.vo.InventorySettlementVO;
import java.util.List;
/**
 * 库存结算单  ENTITY <=> VO / Form / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface InventorySettlementConvert  {
    List<InventorySettlementVO> dos2vos(List<InventorySettlement> list);
}
