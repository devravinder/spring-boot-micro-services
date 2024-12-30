package com.paravar.web.controllers;

import com.paravar.domain.PagedResult;
import com.paravar.domain.Product;
import com.paravar.domain.ProductNotFoundException;
import com.paravar.domain.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
@AllArgsConstructor
class ProductController {
    private final ProductService productService;

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        log.info("Fetching products for page: {}", pageNo);
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    Product getProductByCode(@PathVariable String code) {
        log.info("Fetching product for code: {}", code);
        return productService.getProductByCode(code).orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
