package com.favtuts.sb.conditionalbeans.conditionalonjndi;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
import org.springframework.context.annotation.Configuration;

// Load a bean only if a certain resource is available via JNDI:
@Configuration
@ConditionalOnJndi("java:comp/env/foo")
class OnJndiModule {
}