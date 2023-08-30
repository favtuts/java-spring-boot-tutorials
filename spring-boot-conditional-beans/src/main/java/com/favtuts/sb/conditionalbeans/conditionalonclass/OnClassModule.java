package com.favtuts.sb.conditionalbeans.conditionalonclass;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

// Load a bean only if a certain class is on the classpath:
@Configuration
@ConditionalOnClass(name = "this.clazz.does.not.Exist")
public class OnClassModule {
}
