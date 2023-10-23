package com.ems.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class GatewayRunApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayRunApp.class, args);
    }
}
