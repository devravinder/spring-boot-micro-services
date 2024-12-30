package com.paravar.domain;

import org.mapstruct.Mapper;

@Mapper
interface ProductMapper {
    Product toProduct(ProductEntity productEntity);
}
