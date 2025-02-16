package com.microservices.productservice.service.impl;

import com.microservices.productservice.exception.ProductAlreadyExistException;
import com.microservices.productservice.exception.ProductNotFoundException;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductUpdateRequest;
import com.microservices.productservice.model.product.entity.ProductEntity;
import com.microservices.productservice.model.product.mapper.ProductEntityToProductMapper;
import com.microservices.productservice.model.product.mapper.ProductUpdateRequestToProductEntityMapper;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductRepository productRepository;

    private final ProductUpdateRequestToProductEntityMapper productUpdateRequestToProductEntityMapper =
            ProductUpdateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper =
            ProductEntityToProductMapper.initialize();

    @Override
    public Product updateProductById(String productId, ProductUpdateRequest productUpdateRequest) {

        checkProductNameUniqueness(productUpdateRequest.getName());
        final ProductEntity productEntityToBeUpdate = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        productUpdateRequestToProductEntityMapper.mapForUpdating(productEntityToBeUpdate, productUpdateRequest);
        ProductEntity updatedProductEntity = productRepository.save(productEntityToBeUpdate);
        return productEntityToProductMapper.map(updatedProductEntity);
    }

    private void checkProductNameUniqueness(final String productName) {
        if (productRepository.existsProductEntityByName(productName)) {
            throw new ProductAlreadyExistException("With given product name = " + productName);
        }
    }
}
