package com.favtuts.sb.conditionalbeans.applyingconditionals;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

// All beans contained within this configuration will only be loaded if the condition is met
@Configuration
@ConditionalOnProperty("configuration.enabled")
public class ConditionalConfiguration {
}
