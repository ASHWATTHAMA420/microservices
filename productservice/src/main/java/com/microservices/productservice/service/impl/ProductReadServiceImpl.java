package com.microservices.productservice.service.impl;

import com.microservices.productservice.exception.ProductNotFoundException;
import com.microservices.productservice.model.common.CustomPage;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductPagingRequest;
import com.microservices.productservice.model.product.entity.ProductEntity;
import com.microservices.productservice.model.product.mapper.ListProductEntityToListProductMapper;
import com.microservices.productservice.model.product.mapper.ProductEntityToProductMapper;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReadServiceImpl implements ProductReadService {

    private final ProductRepository productRepository;
    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    private final ListProductEntityToListProductMapper listProductEntityToListProductMapper =
            ListProductEntityToListProductMapper.initialize();

    @Override
    public Product getProductById(String productId) {

        final ProductEntity productEntityFromDB = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("With given productID = " + productId));

        return productEntityToProductMapper.map(productEntityFromDB);
    }

    @Override
    public CustomPage<Product> getProducts(ProductPagingRequest productPagingRequest) {

        final Page<ProductEntity> productEntityPage = productRepository.findAll(productPagingRequest.toPageable());
        if (productEntityPage.getContent().isEmpty()) {
            throw new ProductNotFoundException("Couldn't find any Product");
        }
        final List<Product> productDomainModels = listProductEntityToListProductMapper
                .toProductList(productEntityPage.getContent());
        return CustomPage.of(productDomainModels, productEntityPage);
    }
}
