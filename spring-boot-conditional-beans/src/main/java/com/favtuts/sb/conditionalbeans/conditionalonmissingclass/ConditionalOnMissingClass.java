package com.favtuts.sb.conditionalbeans.conditionalonmissingclass;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingClass(value = "this.clazz.does.not.Exist")
class OnMissingClassModule {

}
