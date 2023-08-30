package com.favtuts.sb.conditionalbeans.customcondition;

import org.apache.commons.lang3.SystemUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

// Implement a condition that loads beans only if we’re running the code on a Windows machine
public class OnWindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return SystemUtils.IS_OS_WINDOWS;
    }
}
