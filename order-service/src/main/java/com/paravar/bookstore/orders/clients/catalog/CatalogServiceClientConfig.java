package com.paravar.bookstore.orders.clients.catalog;

import com.paravar.bookstore.orders.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Configuration
class CatalogServiceClientConfig {

    @Bean
    RestClient restClient(ApplicationProperties properties) {

       var simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
       simpleClientHttpRequestFactory.setConnectTimeout(Duration.ofSeconds(5)); // waiting time to establish connection
       simpleClientHttpRequestFactory.setReadTimeout(Duration.ofSeconds(5));
       // after the connection is established, how long to wait for a response

        return RestClient.builder()
                .baseUrl(properties.catalogServiceUrl())
                .requestFactory(simpleClientHttpRequestFactory)
                .build();
    }

}
