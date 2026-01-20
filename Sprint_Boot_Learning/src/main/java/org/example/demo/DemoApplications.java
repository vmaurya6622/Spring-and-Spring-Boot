package org.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // enables component scanning + auto-configuration + configuration
public class DemoApplications {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplications.class, args);
    }
}


