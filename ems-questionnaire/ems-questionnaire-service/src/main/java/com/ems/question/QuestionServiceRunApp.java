package com.ems.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScans({
        @ComponentScan("com.ems.config"),
        @ComponentScan("common")
})
@EnableFeignClients(basePackages = "com.ems.api.client")
public class QuestionServiceRunApp {
    public static void main(String[] args) {
        SpringApplication.run(QuestionServiceRunApp.class, args);
    }
}
