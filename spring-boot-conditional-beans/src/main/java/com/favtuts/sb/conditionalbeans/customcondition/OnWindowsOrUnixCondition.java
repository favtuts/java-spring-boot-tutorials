package com.favtuts.sb.conditionalbeans.customcondition;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.context.annotation.Conditional;

public class OnWindowsOrUnixCondition extends AnyNestedCondition {

    OnWindowsOrUnixCondition() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @Conditional(OnWindowsCondition.class)
    static class OnWindows {
    }

    @Conditional(OnUnixCondition.class)
    static class OnUnix {
    }
}
