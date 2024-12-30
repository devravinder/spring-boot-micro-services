package com.paravar.bookstore.catalog.domain;

import org.mapstruct.Mapper;

@Mapper
interface ProductMapper {
    Product toProduct(ProductEntity productEntity);
}
