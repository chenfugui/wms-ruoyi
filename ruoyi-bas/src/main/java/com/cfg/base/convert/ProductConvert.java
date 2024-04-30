package com.cfg.base.convert;

import org.mapstruct.Mapper;
import com.cfg.base.domain.Product;
import com.cfg.base.pojo.dto.ProductDTO;
import com.cfg.base.pojo.vo.ProductVO;
import java.util.List;
/**
 * 产品信息表  DO <=> DTO <=> VO / BO / Query
 *
 * @author chenfg
 */
@Mapper(componentModel = "spring")
public interface ProductConvert  {

    /**
     * @param source DO
     * @return DTO
     */
    ProductDTO do2dto(Product source);

    /**
     * @param source DTO
     * @return DO
     */
    Product dto2do(ProductDTO source);

    List<ProductVO> dos2vos(List<Product> list);
}
