package com.paravar.bookstore.orders.domain;

import com.paravar.bookstore.orders.clients.catalog.Product;
import com.paravar.bookstore.orders.clients.catalog.ProductServiceClient;
import com.paravar.bookstore.orders.domain.models.CreateOrderRequest;
import com.paravar.bookstore.orders.domain.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@AllArgsConstructor
class OrderValidator {

    private final ProductServiceClient client;

    void validate(CreateOrderRequest request) {
        Set<OrderItem> items = request.items();
        for (OrderItem item : items) {
            Product product = client.getProductByCode(item.code())
                    .orElseThrow(() -> new InvalidOrderException("Invalid Product code:" + item.code()));
            if (item.price().compareTo(product.price()) != 0) {
                log.error(
                        "Product price not matching. Actual price:{}, received price:{}",
                        product.price(),
                        item.price());
                throw new InvalidOrderException("Product price not matching");
            }
        }
    }
}
