package com.favtuts.sb.conditionalbeans.conditionalonwebapplication;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Configuration;

// Load a bean only if we’re not running inside a web application:
@Configuration
@ConditionalOnNotWebApplication
public class OnNotWebApplicationModule {
}
