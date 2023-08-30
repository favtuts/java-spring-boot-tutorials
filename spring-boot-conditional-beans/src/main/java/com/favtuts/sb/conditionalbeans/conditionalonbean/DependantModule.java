package com.favtuts.sb.conditionalbeans.conditionalonbean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// load a bean only if a certain other bean is available in the application context
@Configuration
public class DependantModule {

    @Bean
    OptionalBean optionalBean() {
        return new OptionalBean();
    }

    @Bean
    @ConditionalOnBean(OptionalBean.class)
    DependantBean dependantBean() {
        return new DependantBean();
    }

    static class DependantBean {

    }

    static class OptionalBean {

    }
}
