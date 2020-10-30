package com.bigcart.productservice.bigcartproductservice;

import com.bigcart.productservice.bigcartproductservice.Controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.File;

@SpringBootApplication
@EnableDiscoveryClient
public class BigcartProductServiceApplication {

    public static void main(String[] args) {
        new File(ProductController.uploadDirectory).mkdir();
        SpringApplication.run(BigcartProductServiceApplication.class, args);
    }

}
