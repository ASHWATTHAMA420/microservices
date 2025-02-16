package com.microservices.productservice.model.product.mapper;

import com.microservices.productservice.model.common.mapper.BaseMapper;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductToProductEntityMapper extends BaseMapper<Product, ProductEntity> {

    @Override
    ProductEntity map(Product source);

    static ProductToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductToProductEntityMapper.class);
    }

}
