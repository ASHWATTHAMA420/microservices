package com.microservices.productservice.model.product.mapper;

import com.microservices.productservice.model.common.mapper.BaseMapper;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToProductResponseMapper extends BaseMapper<Product, ProductResponse> {

    @Override
    ProductResponse map(Product source);

    static ProductToProductResponseMapper initialize() {
        return Mappers.getMapper(ProductToProductResponseMapper.class);
    }
}
