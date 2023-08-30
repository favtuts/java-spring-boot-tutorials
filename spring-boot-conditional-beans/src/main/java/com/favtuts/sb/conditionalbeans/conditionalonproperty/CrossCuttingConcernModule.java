package com.favtuts.sb.conditionalbeans.conditionalonproperty;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

// It allows to load beans conditionally depending on a certain environment property
@Configuration
@ConditionalOnProperty(value = "module.enabled", havingValue = "true", matchIfMissing = true)
public class CrossCuttingConcernModule {
}