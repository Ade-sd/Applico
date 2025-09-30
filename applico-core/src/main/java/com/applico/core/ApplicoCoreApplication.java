package com.applico.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ApplicoCoreApplication {

    static void main(String[] args) {
        SpringApplication.run(ApplicoCoreApplication.class, args);
    }

}
