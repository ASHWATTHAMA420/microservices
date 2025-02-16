package com.microservices.productservice.service.impl;

import com.microservices.productservice.exception.ProductNotFoundException;
import com.microservices.productservice.model.product.entity.ProductEntity;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteServiceImpl implements ProductDeleteService {

    private final ProductRepository productRepository;

    @Override
    public void deleteProductById(String productId) {

        final ProductEntity productEntityToBeDelete = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));
        productRepository.delete(productEntityToBeDelete);
    }
}

