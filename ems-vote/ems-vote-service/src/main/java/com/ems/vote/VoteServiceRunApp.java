package com.ems.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScans(@ComponentScan("com.ems.config"))
public class VoteServiceRunApp {
    public static void main(String[] args) {
        SpringApplication.run(VoteServiceRunApp.class, args);
    }
}
