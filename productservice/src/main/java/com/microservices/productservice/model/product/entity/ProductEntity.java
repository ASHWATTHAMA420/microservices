package com.microservices.productservice.model.product.entity;

import com.microservices.productservice.model.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AMOUNT", precision = 24, scale = 4)
    private BigDecimal amount;

    @Column(name = "UNIT_PRICE", precision = 24, scale = 4)
    private BigDecimal unitPrice;
}
