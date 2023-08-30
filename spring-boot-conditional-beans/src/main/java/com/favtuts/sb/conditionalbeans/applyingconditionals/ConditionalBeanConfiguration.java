package com.favtuts.sb.conditionalbeans.applyingconditionals;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalBeanConfiguration {

    // This bean is only loaded if the condition is met
    @Bean
    @ConditionalOnProperty("conditionalBean.enabled")
    ConditionalBean conditionalBean() {
        return new ConditionalBean();
    }

    static class ConditionalBean {

    }

}
