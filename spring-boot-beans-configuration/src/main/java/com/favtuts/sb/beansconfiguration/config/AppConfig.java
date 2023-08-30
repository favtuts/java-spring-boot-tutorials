package com.favtuts.sb.beansconfiguration.config;

import com.favtuts.sb.beansconfiguration.service.MultiplierService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = {"multiplierService", "multiplierService-alias1", "multiplierService-alias2"})
    public MultiplierService multiplier() {
        return new MultiplierService(2);
    }
}
