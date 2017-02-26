package com.jrogalsk.requestsmanager.core.business.application;

import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionTrigger;

import static java.util.Objects.nonNull;

public class StateTransitionJustificationVerifier {
    public boolean hasValidJustification(StateTransitionTrigger stateTransitionTrigger, String stateTransitionJustification) {
        if (this.justificationIsRequiredFor(stateTransitionTrigger)) {
            return nonNull(stateTransitionJustification) && !stateTransitionJustification.isEmpty();
        }
        else {
            return true;
        }
    }

    private boolean justificationIsRequiredFor(StateTransitionTrigger stateTransitionTrigger) {
        return stateTransitionTrigger == StateTransitionTrigger.DELETE || stateTransitionTrigger == StateTransitionTrigger.REJECT;
    }
}
