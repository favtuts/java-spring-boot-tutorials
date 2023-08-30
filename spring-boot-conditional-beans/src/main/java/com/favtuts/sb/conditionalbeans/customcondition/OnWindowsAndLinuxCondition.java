package com.favtuts.sb.conditionalbeans.customcondition;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.context.annotation.Conditional;

// created a condition that is satisfied if the application runs on windows or unix.
public class OnWindowsAndLinuxCondition extends AllNestedConditions {

    OnWindowsAndLinuxCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @Conditional(OnWindowsCondition.class)
    static class OnWindows {
    }

    @Conditional(OnUnixCondition.class)
    static class OnLinux {
    }
}
