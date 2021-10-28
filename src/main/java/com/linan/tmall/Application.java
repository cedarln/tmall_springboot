package com.linan.tmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello tmall_springboot!");
        SpringApplication.run(Application.class, args);
    }
}
