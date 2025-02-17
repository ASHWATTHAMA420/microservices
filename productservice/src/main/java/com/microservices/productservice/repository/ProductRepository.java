package com.microservices.productservice.repository;

import com.microservices.productservice.model.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    boolean existsProductEntityByName(final String name);
    Optional<ProductEntity> findProductEntityByName(String name);

}
