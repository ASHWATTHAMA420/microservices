package com.microservices.productservice.model.product.dto.request;

import com.microservices.productservice.model.common.dto.request.CustomPagingRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductPagingRequest extends CustomPagingRequest {


}
