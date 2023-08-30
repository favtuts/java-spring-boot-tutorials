package com.favtuts.sb.conditionalbeans.conditionalonwebapplication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

// Load a bean only if we’re running inside a web application:
@Configuration
@ConditionalOnWebApplication
public class OnWebApplicationModule {
}
