package com.jrogalsk.requestsmanager.core.business.application.request;

import com.jrogalsk.requestsmanager.core.business.domain.statetransition.IllegalStateTransitionException;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.State;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionTrigger;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransition;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionsRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StateTransitionsRepositoryTest {
    private static final List<StateTransition> ALL_POSSIBLE_STATE_TRANSITIONS =
            StateTransitionsRepositoryTest.generateAllPossibleStateTransitions();

    private final StateTransitionsRepository stateTransitionsRepository = new StateTransitionsRepository();

    @Test
    public void transitionsForState_CREATE() {
        List<StateTransition> supportedTransitions = this.findTransitionsFor(State.CREATED);

        assertThat(supportedTransitions.size(), is(2));
        assertTrue(supportedTransitions.contains(new StateTransition(State.CREATED, StateTransitionTrigger.DELETE, State.DELETED)));
        assertTrue(supportedTransitions.contains(new StateTransition(State.CREATED, StateTransitionTrigger.VERIFY, State.VERIFIED)));
    }

    @Test
    public void transitionsForState_DELETED() {
        assertThat(this.findTransitionsFor(State.DELETED).size(), is(0));
    }

    @Test
    public void transitionsForState_VERIFIED() {
        List<StateTransition> supportedTransitions = this.findTransitionsFor(State.VERIFIED);

        assertThat(supportedTransitions.size(), is(2));
        assertTrue(supportedTransitions.contains(new StateTransition(State.VERIFIED, StateTransitionTrigger.REJECT, State.REJECTED)));
        assertTrue(supportedTransitions.contains(new StateTransition(State.VERIFIED, StateTransitionTrigger.ACCEPT, State.ACCEPTED)));
    }

    @Test
    public void transitionsForState_REJECTED() {
        assertThat(this.findTransitionsFor(State.REJECTED).size(), is(0));
    }

    @Test
    public void transitionsForState_ACCEPTED() {
        List<StateTransition> supportedTransitions = this.findTransitionsFor(State.ACCEPTED);

        assertThat(supportedTransitions.size(), is(2));
        assertTrue(supportedTransitions.contains(new StateTransition(State.ACCEPTED, StateTransitionTrigger.REJECT, State.REJECTED)));
        assertTrue(supportedTransitions.contains(new StateTransition(State.ACCEPTED, StateTransitionTrigger.PUBLISH, State.PUBLISHED)));
    }

    @Test
    public void transitionsForState_PUBLISHED() {
        assertThat(this.findTransitionsFor(State.PUBLISHED).size(), is(0));
    }

    private List<StateTransition> findTransitionsFor(State aState) {
        return StateTransitionsRepositoryTest.ALL_POSSIBLE_STATE_TRANSITIONS.stream()
                .filter(t -> t.getStartState() == aState)
                .filter(t -> this.isSupportedTransition(t.getStartState(), t.getTrigger(), t.getTargetState()))
                .collect(Collectors.toList());
    }

    private boolean isSupportedTransition(State fromState, StateTransitionTrigger stateTransitionTrigger, State toState) {
        try {
            StateTransition transition = this.stateTransitionsRepository()
                    .findTransitionFor(fromState, stateTransitionTrigger);
            return transition.getTargetState() == toState;
        }
        catch (IllegalStateTransitionException e) {
            return false;
        }
    }

    private StateTransitionsRepository stateTransitionsRepository() {
        return this.stateTransitionsRepository;
    }

    private static List<StateTransition> generateAllPossibleStateTransitions() {
        List<StateTransition> stateTransitions = new ArrayList<>();

        for (State fromState : State.values()) {
            for (State toState : State.values()) {
                for (StateTransitionTrigger stateTransitionTrigger : StateTransitionTrigger.values()) {
                    stateTransitions.add(new StateTransition(fromState, stateTransitionTrigger, toState));
                }
            }
        }

        return stateTransitions;
    }

}
