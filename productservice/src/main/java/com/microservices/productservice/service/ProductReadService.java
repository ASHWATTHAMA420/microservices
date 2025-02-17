package com.microservices.productservice.service;

import com.microservices.productservice.model.common.CustomPage;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductPagingRequest;

public interface ProductReadService {
    Product getProductById(final String productId);
    CustomPage<Product> getProducts(final ProductPagingRequest productPagingRequest);
    Product getProductByName(final String productName);
}
