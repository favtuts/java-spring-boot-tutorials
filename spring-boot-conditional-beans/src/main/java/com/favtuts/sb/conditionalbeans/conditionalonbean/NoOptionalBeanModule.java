package com.favtuts.sb.conditionalbeans.conditionalonbean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoOptionalBeanModule {

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
