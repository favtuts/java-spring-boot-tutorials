package com.favtuts.sb.conditionalbeans.applyingconditionals;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

// Add a condition to any bean declared with one of the stereotype annotations @Component, @Service, @Repository, or @Controller.
@Component
@ConditionalOnProperty("conditionalComponent.enabled")
public class ConditionalComponent {
}
