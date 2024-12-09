package com.fms.fmsback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.fms.fmsback")
public class FmsBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmsBackApplication.class, args);
    }

}
