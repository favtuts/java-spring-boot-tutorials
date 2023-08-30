package com.favtuts.sb.conditionalbeans.conditionalonresource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;

// load a bean depending on the fact that a certain resource is available on the class path
// The LogbackModule is only loaded if the logback configuration file was found on the classpath
@Configuration
@ConditionalOnResource(resources = "/logback.xml")
public class LogbackModule {
}
