package com.jrogalsk.requestsmanager.core.business.domain.transition;

import com.jrogalsk.requestsmanager.core.business.domain.request.State;

public class IllegalStateTransitionException extends RuntimeException {
    public IllegalStateTransitionException(State aState, StateTransitionTrigger aStateTransitionTrigger) {
        super(String.format("Can't %s request with state %s", aStateTransitionTrigger, aState));
    }
}
