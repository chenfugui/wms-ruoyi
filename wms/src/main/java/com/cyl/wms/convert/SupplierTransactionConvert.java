package com.cyl.wms.convert;

import com.cyl.wms.domain.entity.SupplierTransaction;
import org.mapstruct.Mapper;
import com.cyl.wms.pojo.vo.SupplierTransactionVO;
import java.util.List;
/**
 * 供应商账户流水  ENTITY <=> VO / Form / Query
 *
 * @author zcc
 */
@Mapper(componentModel = "spring")
public interface SupplierTransactionConvert  {
    List<SupplierTransactionVO> dos2vos(List<SupplierTransaction> list);
}
