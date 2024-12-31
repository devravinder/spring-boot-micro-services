package com.paravar.bookstore.orders.web.controllers;

import com.paravar.bookstore.orders.domain.OrderNotFoundException;
import com.paravar.bookstore.orders.domain.OrderService;
import com.paravar.bookstore.orders.domain.SecurityService;
import com.paravar.bookstore.orders.domain.models.CreateOrderRequest;
import com.paravar.bookstore.orders.domain.models.CreateOrderResponse;
import com.paravar.bookstore.orders.domain.models.OrderDTO;
import com.paravar.bookstore.orders.domain.models.OrderSummary;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@AllArgsConstructor
class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String userName = securityService.getLoginUserName();
        log.info("Creating order for user: {}", userName);
        return orderService.createOrder(userName, request);
    }

    @GetMapping
    List<OrderSummary> getOrders() {
        String userName = securityService.getLoginUserName();
        log.info("Fetching orders for user: {}", userName);
        return orderService.findOrders(userName);
    }

    @GetMapping(value = "/{orderNumber}")
    OrderDTO getOrder(@PathVariable(value = "orderNumber") String orderNumber) {
        log.info("Fetching order by id: {}", orderNumber);
        String userName = securityService.getLoginUserName();
        return orderService
                .findUserOrder(userName, orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }
}
