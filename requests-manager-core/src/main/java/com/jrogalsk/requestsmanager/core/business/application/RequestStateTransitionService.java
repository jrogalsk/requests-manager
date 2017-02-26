package com.jrogalsk.requestsmanager.core.business.application;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionTrigger;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionsRepository;

public class RequestStateTransitionService {
    private StateTransitionsRepository stateTransitionsRepository = new StateTransitionsRepository();

    public void performTransition(Request request, StateTransitionTrigger targetState, String aStateTransitionJustification) {
        this.stateTransitionsRepository()
                .findTransitionFor(request.getState(), targetState)
                .runOn(request, aStateTransitionJustification);
    }

    private StateTransitionsRepository stateTransitionsRepository() {
        return this.stateTransitionsRepository;
    }

}
