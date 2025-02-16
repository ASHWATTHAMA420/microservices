package com.microservices.productservice.service.impl;


import com.microservices.productservice.exception.ProductAlreadyExistException;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductCreateRequest;
import com.microservices.productservice.model.product.entity.ProductEntity;
import com.microservices.productservice.model.product.mapper.ProductCreateRequestToProductEntityMapper;
import com.microservices.productservice.model.product.mapper.ProductEntityToProductMapper;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateServiceImpl implements ProductCreateService {

    private final ProductRepository productRepository;

    private final ProductCreateRequestToProductEntityMapper productCreateRequestToProductEntityMapper =
            ProductCreateRequestToProductEntityMapper.initialize();

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    @Override
    public Product createProduct(ProductCreateRequest productCreateRequest) {

        checkUniquenessProductName(productCreateRequest.getName());
        final ProductEntity productEntityToBeSave = productCreateRequestToProductEntityMapper.mapForSaving(productCreateRequest);
        ProductEntity savedProductEntity = productRepository.save(productEntityToBeSave);
        return productEntityToProductMapper.map(savedProductEntity);
    }

    private void checkUniquenessProductName(final String productName) {
        if (productRepository.existsProductEntityByName(productName)) {
            throw new ProductAlreadyExistException("There is another product with given name: " + productName);
        }
    }
}
