package com.example.lab1_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Lab1ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1ServiceApplication.class, args);
    }

}
