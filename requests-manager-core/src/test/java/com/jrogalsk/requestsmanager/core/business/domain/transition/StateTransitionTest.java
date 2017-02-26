package com.jrogalsk.requestsmanager.core.business.domain.transition;

import com.jrogalsk.requestsmanager.core.business.domain.request.Request;
import com.jrogalsk.requestsmanager.core.business.domain.request.State;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class StateTransitionTest {
    @Test
    public void considerAsEqualIfAllAttributesAreEqual() {
        assertThat(new StateTransition(State.ACCEPTED, StateTransitionTrigger.DELETE, State.DELETED),
                is(new StateTransition(State.ACCEPTED, StateTransitionTrigger.DELETE, State.DELETED)));
    }

    @Test
    public void considerAsNotEqualIfFoundMismatchOnAnyAttribute() {
        assertThat(new StateTransition(State.ACCEPTED, StateTransitionTrigger.DELETE, State.DELETED),
                not(new StateTransition(State.REJECTED, StateTransitionTrigger.DELETE, State.DELETED)));

        assertThat(new StateTransition(State.ACCEPTED, StateTransitionTrigger.DELETE, State.DELETED),
                not(new StateTransition(State.ACCEPTED, StateTransitionTrigger.CREATE, State.DELETED)));

        assertThat(new StateTransition(State.ACCEPTED, StateTransitionTrigger.DELETE, State.DELETED),
                not(new StateTransition(State.ACCEPTED, StateTransitionTrigger.DELETE, State.PUBLISHED)));
    }

    @Test
    public void performTransition() {
        Request request = new Request("moo", "boo");

        new StateTransition(State.CREATED, StateTransitionTrigger.DELETE, State.DELETED).runOn(request);

        assertThat(request.getState(), is(State.DELETED));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doNotPerformTransitionIfRequestHasUnsupportedState() {
        Request request = new Request("moo", "boo");

        new StateTransition(State.REJECTED, StateTransitionTrigger.DELETE, State.DELETED).runOn(request);
    }

}
