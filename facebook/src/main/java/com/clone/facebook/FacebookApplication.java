package com.clone.facebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
public class FacebookApplication {

    public static void main(String[] args) {

        SpringApplication.run(FacebookApplication.class, args);
    }

}
