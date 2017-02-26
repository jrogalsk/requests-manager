package com.jrogalsk.requestsmanager.core.business.domain.statetransition;

import java.util.Arrays;
import java.util.List;

public class StateTransitionsRepository {
    private final List<StateTransition> supportedStateTransitions = Arrays.asList(
            new StateTransition(State.CREATED, StateTransitionTrigger.DELETE, State.DELETED),
            new StateTransition(State.CREATED, StateTransitionTrigger.VERIFY, State.VERIFIED),
            new StateTransition(State.VERIFIED, StateTransitionTrigger.REJECT, State.REJECTED),
            new StateTransition(State.VERIFIED, StateTransitionTrigger.ACCEPT, State.ACCEPTED),
            new StateTransition(State.ACCEPTED, StateTransitionTrigger.REJECT, State.REJECTED),
            new StateTransition(State.ACCEPTED, StateTransitionTrigger.PUBLISH, State.PUBLISHED)
    );

    public StateTransition findTransitionFor(State aState, StateTransitionTrigger stateTransitionTrigger) {
        return this.supportedStateTransitions().stream()
                .filter(transition -> transition.getStartState() == aState)
                .filter(transition -> transition.getTrigger() == stateTransitionTrigger)
                .findFirst()
                .orElseThrow(() -> new IllegalStateTransitionException(aState, stateTransitionTrigger));
    }

    private List<StateTransition> supportedStateTransitions() {
        return this.supportedStateTransitions;
    }

}
