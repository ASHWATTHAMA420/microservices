package com.microservices.productservice.model.product;

import com.microservices.productservice.model.common.BaseDomainModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseDomainModel {

    private String id;
    private String name;
    private BigDecimal amount;
    private BigDecimal unitPrice;

}
