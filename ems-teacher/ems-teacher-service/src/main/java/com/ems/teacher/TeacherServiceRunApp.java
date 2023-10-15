package com.ems.teacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScans(@ComponentScan("com.ems.config"))
@EnableFeignClients(basePackages = "com.ems.api.client")
public class TeacherServiceRunApp {
    public static void main(String[] args) {
        SpringApplication.run(TeacherServiceRunApp.class, args);
    }
}
