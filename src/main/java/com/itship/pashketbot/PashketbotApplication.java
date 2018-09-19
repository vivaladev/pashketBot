package com.itship.pashketbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
@EnableScheduling
@Import(com.itship.pashketbot.config.BotConfiguration.class)
public class PashketbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(PashketbotApplication.class, args);
    }
}
