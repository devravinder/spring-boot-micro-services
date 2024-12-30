package com.paravar.bookstore.catalog;

import org.springframework.boot.SpringApplication;

/*
 This is to run the application in the test container ( for local development )
  - this is useful to run the current service with all required containers
     - without running entire application containers

*/
public class TestCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CatalogServiceApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}
