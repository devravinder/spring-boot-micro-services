package com.paravar.bookstore.notifications;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.paravar.bookstore.notifications.domain.NotificationService;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
public abstract class AbstractIT {
    @Mock
    protected NotificationService notificationService;
}
