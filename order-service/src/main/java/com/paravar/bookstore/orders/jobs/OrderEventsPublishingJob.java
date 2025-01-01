package com.paravar.bookstore.orders.jobs;

import com.paravar.bookstore.orders.domain.OrderEventService;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
class OrderEventsPublishingJob {

    private final OrderEventService orderEventService;

    @Scheduled(cron = "${orders.publish-order-events-job-cron}") // cron job
    @SchedulerLock(name = "publishOrderEvents")
    public void publishOrderEvents() {
        LockAssert.assertLocked();
        log.info("Publishing Order Events at {}", Instant.now());
        orderEventService.publishOrderEvents();
    }
}
