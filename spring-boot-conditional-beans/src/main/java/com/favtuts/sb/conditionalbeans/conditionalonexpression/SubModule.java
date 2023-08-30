package com.favtuts.sb.conditionalbeans.conditionalonexpression;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;

// create sub modules that should be disabled if the parent module is disabled, but can also be disabled if the parent module is enabled.
@Configuration
@ConditionalOnExpression("${module.enabled:true} " +
        "and ${module.submodule.enabled:true}")
public class SubModule {
}