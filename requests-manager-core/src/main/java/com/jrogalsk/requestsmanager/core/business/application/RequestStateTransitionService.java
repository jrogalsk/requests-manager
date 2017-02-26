package com.jrogalsk.requestsmanager.core.business.application;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.IllegalStateTransitionException;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionTrigger;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionsRepository;

public class RequestStateTransitionService {
    private StateTransitionsRepository stateTransitionsRepository = new StateTransitionsRepository();
    private StateTransitionJustificationVerifier stateTransitionJustificationVerifier = new StateTransitionJustificationVerifier();

    public void performTransition(Request request, StateTransitionTrigger aTrigger, String aStateTransitionJustification) {
        if (this.hasValidJustification(aTrigger, aStateTransitionJustification)) {
            this.runStateTransitionFor(request, aTrigger, aStateTransitionJustification);
        }
        else {
            throw new IllegalStateTransitionException(String.format("Justification is required for %s action", aTrigger));
        }
    }

    private void runStateTransitionFor(Request request, StateTransitionTrigger aTrigger, String aStateTransitionJustification) {
        this.stateTransitionsRepository()
                .findTransitionFor(request.getState(), aTrigger)
                .runOn(request, aStateTransitionJustification);
    }

    private boolean hasValidJustification(StateTransitionTrigger aTrigger, String aStateTransitionJustification) {
        return this.stateTransitionJustificationVerifier()
                .hasValidJustification(aTrigger, aStateTransitionJustification);
    }

    private StateTransitionJustificationVerifier stateTransitionJustificationVerifier() {
        return this.stateTransitionJustificationVerifier;
    }

    private StateTransitionsRepository stateTransitionsRepository() {
        return this.stateTransitionsRepository;
    }

}
