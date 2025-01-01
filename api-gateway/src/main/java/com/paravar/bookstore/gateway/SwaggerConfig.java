package com.paravar.bookstore.gateway;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class SwaggerConfig {

    @Bean
    @Primary
    //  #  custom-info-2

    public SwaggerUiConfigProperties swaggerUiConfigProperties(RouteDefinitionLocator locator) {
        /*
         in this we are dynamically adding child services open api urls to gateway
        * */

        SwaggerUiConfigProperties properties = new SwaggerUiConfigProperties();

        Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();

        List<RouteDefinition> definitions =
                locator.getRouteDefinitions().collectList().block();

        if (null != definitions) {
            definitions.stream()
                    // reading all route ids ...that ends with -service ( see application.yml )
                    // except openapi
                    .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                    .forEach(routeDefinition -> {
                        // remove -service
                        // then orders-service -> orders
                        String name = routeDefinition.getId().replaceAll("-service", "");

                        // adding swagger urls dynamically
                        /*
                         for name: orders, url:/v3/api-docs/orders

                         Note: DEFAULT_API_DOCS_URL = /v3/api-docs
                        * */
                        AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl =
                                new AbstractSwaggerUiConfigProperties.SwaggerUrl(
                                        name, DEFAULT_API_DOCS_URL + "/" + name, null);
                        urls.add(swaggerUrl);
                    });
        }

        properties.setUrls(urls);

        return properties;
    }
}
