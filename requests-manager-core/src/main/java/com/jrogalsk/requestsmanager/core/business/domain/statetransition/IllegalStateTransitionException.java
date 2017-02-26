package com.jrogalsk.requestsmanager.core.business.domain.statetransition;

public class IllegalStateTransitionException extends RuntimeException {
    public IllegalStateTransitionException(String message) {
        super(message);
    }

    public IllegalStateTransitionException(State aState, StateTransitionTrigger aStateTransitionTrigger) {
        super(String.format("Can't %s request with state %s", aStateTransitionTrigger, aState));
    }
}
