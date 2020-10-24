package com.bigcart.productservice.bigcartproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BigcartProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigcartProductServiceApplication.class, args);
    }

}
