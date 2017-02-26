package com.jrogalsk.requestsmanager.core.business.domain.transition;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.request.State;

import java.util.Objects;

import static java.util.Objects.hash;

public class StateTransition {
    private final State startState;
    private final State targetState;
    private final StateTransitionTrigger trigger;

    public StateTransition(State aStartState, StateTransitionTrigger aTrigger, State aTargetState) {
        this.startState = aStartState;
        this.trigger = aTrigger;
        this.targetState = aTargetState;
    }

    public void runOn(Request request) {
        if (request.getState() == this.getStartState()) {
            request.setState(this.getTargetState());
        }
        else {
            throw new IllegalArgumentException(
                    String.format("Transition can not be run on Request with '%s' state", request.getState()));
        }
    }

    public State getStartState() {
        return this.startState;
    }

    public StateTransitionTrigger getTrigger() {
        return this.trigger;
    }

    public State getTargetState() {
        return this.targetState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateTransition)) return false;

        StateTransition that = (StateTransition) o;

        return Objects.equals(this.getStartState(), that.getStartState()) &&
                Objects.equals(this.getTargetState(), that.getTargetState()) &&
                Objects.equals(this.getTrigger(), that.getTrigger());
    }

    @Override
    public int hashCode() {
        return hash(this.getStartState(), this.getTargetState(), this.getTrigger());
    }
}
