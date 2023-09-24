package com.ems.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SystemServiceRunApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemServiceRunApp.class, args);
    }
}
