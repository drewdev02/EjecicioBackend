package com.demo.ejeciciobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.demo.ejeciciobackend.repository")
@EntityScan("com.demo.ejeciciobackend.models")
public class EjecicioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjecicioBackendApplication.class, args);
    }

}
