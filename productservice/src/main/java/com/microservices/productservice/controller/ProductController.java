package com.microservices.productservice.controller;

import com.microservices.productservice.model.common.CustomPage;
import com.microservices.productservice.model.common.dto.response.CustomPagingResponse;
import com.microservices.productservice.model.common.dto.response.CustomResponse;
import com.microservices.productservice.model.product.Product;
import com.microservices.productservice.model.product.dto.request.ProductCreateRequest;
import com.microservices.productservice.model.product.dto.request.ProductPagingRequest;
import com.microservices.productservice.model.product.dto.request.ProductUpdateRequest;
import com.microservices.productservice.model.product.dto.response.ProductResponse;
import com.microservices.productservice.model.product.mapper.CustomPageToCustomPagingResponseMapper;
import com.microservices.productservice.model.product.mapper.ProductToProductResponseMapper;
import com.microservices.productservice.service.ProductCreateService;
import com.microservices.productservice.service.ProductDeleteService;
import com.microservices.productservice.service.ProductReadService;
import com.microservices.productservice.service.ProductUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductCreateService productCreateService;
    private final ProductReadService productReadService;
    private final ProductUpdateService productUpdateService;
    private final ProductDeleteService productDeleteService;

    private final ProductToProductResponseMapper productToProductResponseMapper = ProductToProductResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CachePut(value = "product", key = "#productCreateRequest.name")
    public CustomResponse<String> createProduct(@RequestBody @Valid final ProductCreateRequest productCreateRequest) {
        final Product createdProduct = productCreateService
                .createProduct(productCreateRequest);
        return CustomResponse.successOf(createdProduct.getId());
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Cacheable(value = "product", key = "#productId")
    public CustomResponse<ProductResponse> getProductById(@PathVariable @UUID final String productId) {
        final Product product = productReadService.getProductById(productId);
        final ProductResponse productResponse = productToProductResponseMapper.map(product);
        return CustomResponse.successOf(productResponse);
    }

    @GetMapping("/name")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Cacheable(value = "product", key = "#productName")
    public CustomResponse<ProductResponse> getProductByName(@RequestBody final String productName) {
        final Product product = productReadService.getProductByName(productName);
        final ProductResponse productResponse = productToProductResponseMapper.map(product);
        return CustomResponse.successOf(productResponse);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<ProductResponse>> getProducts(
            @RequestBody @Valid final ProductPagingRequest productPagingRequest) {
        final CustomPage<Product> productPage = productReadService.getProducts(productPagingRequest);
        final CustomPagingResponse<ProductResponse> productPagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(productPage);
        return CustomResponse.successOf(productPagingResponse);

    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @CachePut(value = "product", key = "#productId")
    public CustomResponse<ProductResponse> updatedProductById(
            @RequestBody @Valid final ProductUpdateRequest productUpdateRequest,
            @PathVariable @UUID final String productId) {
        final Product updatedProduct = productUpdateService.updateProductById(productId, productUpdateRequest);
        final ProductResponse productResponse = productToProductResponseMapper.map(updatedProduct);
        return CustomResponse.successOf(productResponse);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> deleteProductById(@PathVariable @UUID final String productId) {
        productDeleteService.deleteProductById(productId);
        return CustomResponse.SUCCESS;
    }
}
