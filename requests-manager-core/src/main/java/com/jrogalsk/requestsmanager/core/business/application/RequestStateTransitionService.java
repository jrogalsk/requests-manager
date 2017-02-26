package com.jrogalsk.requestsmanager.core.business.application;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.transition.StateTransitionTrigger;
import com.jrogalsk.requestsmanager.core.business.domain.transition.StateTransitionsRepository;

public class RequestStateTransitionService {
    private StateTransitionsRepository stateTransitionsRepository = new StateTransitionsRepository();

    public void performTransition(Request request, StateTransitionTrigger targetState) {
        this.stateTransitionsRepository()
                .findTransitionFor(request.getState(), targetState)
                .runOn(request);
    }

    private StateTransitionsRepository stateTransitionsRepository() {
        return this.stateTransitionsRepository;
    }

}
