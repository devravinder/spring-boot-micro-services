package com.paravar.bookstore.orders.jobs;

import com.paravar.bookstore.orders.domain.OrderService;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
class OrderProcessingJob {

    private final OrderService orderService;

    @Scheduled(cron = "${orders.new-orders-job-cron}") // cron job
    @SchedulerLock(name = "processNewOrders")
    public void processNewOrders() {
        LockAssert.assertLocked();
        log.info("Processing new orders at {}", Instant.now());
        orderService.processNewOrders();
    }
}
