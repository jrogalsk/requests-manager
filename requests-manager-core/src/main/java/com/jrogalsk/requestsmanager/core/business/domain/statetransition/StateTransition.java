package com.jrogalsk.requestsmanager.core.business.domain.statetransition;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;

import java.util.Objects;

import static java.util.Objects.hash;
import static java.util.Objects.isNull;

public class StateTransition {
    private final State startState;
    private final State targetState;
    private final StateTransitionTrigger trigger;

    public StateTransition(State aStartState, StateTransitionTrigger aTrigger, State aTargetState) {
        this.startState = aStartState;
        this.trigger = aTrigger;
        this.targetState = aTargetState;
    }

    public void runOn(Request request, String stateChangeJustification) {
        if (request.getState() == this.getStartState()) {
            request.addStateTransitionHistoryEntry(this.createHistoryEntry(stateChangeJustification));
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

    private StateTransitionHistoryEntry createHistoryEntry(String stateChangeJustification) {
        return new StateTransitionHistoryEntry(
                this.getStartState(),
                this.getTargetState(),
                isNull(stateChangeJustification) ? "" : stateChangeJustification);
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
