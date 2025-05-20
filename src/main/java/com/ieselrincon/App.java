package com.ieselrincon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ieselrincon.repository.jpa")  // Especifica el paquete de repositorios JPA
@EnableMongoRepositories(basePackages = "com.ieselrincon.repository.mongo")  // Especifica el paquete de repositorios MongoDB
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}