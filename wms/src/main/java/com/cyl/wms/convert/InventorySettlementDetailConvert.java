package com.cyl.wms.convert;

import com.cyl.wms.domain.entity.InventorySettlementDetail;
import org.mapstruct.Mapper;
import com.cyl.wms.pojo.vo.InventorySettlementDetailVO;
import java.util.List;
/**
 * 库存结算明细  ENTITY <=> VO / Form / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface InventorySettlementDetailConvert  {
    List<InventorySettlementDetailVO> dos2vos(List<InventorySettlementDetail> list);
}
