package com.favtuts.sb.conditionalbeans.conditionalonsinglecandidate;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// only load a bean if a single candidate for the given bean class has been determined
@Configuration
@ConditionalOnSingleCandidate(DataSource.class)
public class OnSingleCandidateModule {
}
