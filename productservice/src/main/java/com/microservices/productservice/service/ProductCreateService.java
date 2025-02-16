package com.microservices.productservice.service;

import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductCreateRequest;

public interface ProductCreateService {
    Product createProduct(final ProductCreateRequest productCreateRequest);
}