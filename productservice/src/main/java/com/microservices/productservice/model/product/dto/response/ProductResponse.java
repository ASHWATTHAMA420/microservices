package com.microservices.productservice.model.product.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private BigDecimal amount;
    private BigDecimal unitPrice;

}
