package com.favtuts.sb.conditionalbeans.conditionalonjava;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;

// Load a bean only if running a certain version of Java:
@Configuration
@ConditionalOnJava(JavaVersion.EIGHT)
public class OnJavaModule {

}
