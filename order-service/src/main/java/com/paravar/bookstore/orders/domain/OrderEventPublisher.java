package com.paravar.bookstore.orders.domain;

import com.paravar.bookstore.orders.ApplicationProperties;
import com.paravar.bookstore.orders.domain.models.OrderCancelledEvent;
import com.paravar.bookstore.orders.domain.models.OrderCreatedEvent;
import com.paravar.bookstore.orders.domain.models.OrderDeliveredEvent;
import com.paravar.bookstore.orders.domain.models.OrderErrorEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationProperties properties;

    public void publish(OrderCreatedEvent event) {
        this.send(properties.newOrdersQueue(), event);
    }

    public void publish(OrderDeliveredEvent event) {
        this.send(properties.deliveredOrdersQueue(), event);
    }

    public void publish(OrderCancelledEvent event) {
        this.send(properties.cancelledOrdersQueue(), event);
    }

    public void publish(OrderErrorEvent event) {
        this.send(properties.errorOrdersQueue(), event);
    }

    private void send(String routingKey, Object payload) {
        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), routingKey, payload);
    }
}
