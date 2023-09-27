package com.ems.subject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScans(@ComponentScan("com.ems.config"))
public class SubjectServiceRunApp {
    public static void main(String[] args) {
        SpringApplication.run(SubjectServiceRunApp.class, args);
    }
}
