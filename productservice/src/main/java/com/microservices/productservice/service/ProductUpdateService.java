package com.microservices.productservice.service;

import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductUpdateRequest;

public interface ProductUpdateService {
    Product updateProductById(final String productId, final ProductUpdateRequest productUpdateRequest);
}
