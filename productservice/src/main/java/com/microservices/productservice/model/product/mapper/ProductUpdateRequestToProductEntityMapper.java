package com.microservices.productservice.model.product.mapper;

import com.microservices.productservice.model.common.mapper.BaseMapper;
import com.microservices.productservice.model.product.dto.request.ProductUpdateRequest;
import com.microservices.productservice.model.product.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductUpdateRequestToProductEntityMapper extends BaseMapper<ProductUpdateRequest, ProductEntity> {

    @Named("mapForUpdating")
    default void mapForUpdating(ProductEntity productEntityToBeUpdate, ProductUpdateRequest productUpdateRequest) {
        productEntityToBeUpdate.setName(productUpdateRequest.getName());
        productEntityToBeUpdate.setAmount(productUpdateRequest.getAmount());
        productEntityToBeUpdate.setUnitPrice(productUpdateRequest.getUnitPrice());
    }

    static ProductUpdateRequestToProductEntityMapper initialize() {
        return Mappers.getMapper(ProductUpdateRequestToProductEntityMapper.class);
    }
}
