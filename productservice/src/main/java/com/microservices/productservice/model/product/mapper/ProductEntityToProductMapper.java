package com.microservices.productservice.model.product.mapper;

import com.microservices.productservice.model.common.mapper.BaseMapper;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductEntityToProductMapper extends BaseMapper<ProductEntity, Product> {

    @Override
    Product map(ProductEntity source);

    static ProductEntityToProductMapper initialize() {
        return Mappers.getMapper(ProductEntityToProductMapper.class);
    }

}
