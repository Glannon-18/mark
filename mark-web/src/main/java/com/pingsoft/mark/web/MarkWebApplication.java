package com.pingsoft.mark.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pingsoft")
@MapperScan("com.pingsoft.mark.mapper")
public class MarkWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarkWebApplication.class, args);
    }

}
