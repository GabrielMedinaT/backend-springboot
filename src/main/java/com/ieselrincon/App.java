package com.ieselrincon;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ieselrincon.repository.jpa")
@EnableMongoRepositories(basePackages = "com.ieselrincon.repository.mongo")
public class App {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));// Cargar las variables de entorno desde el archivo .env

        SpringApplication.run(App.class, args);// Iniciar la aplicación Spring Boot
    }
}
