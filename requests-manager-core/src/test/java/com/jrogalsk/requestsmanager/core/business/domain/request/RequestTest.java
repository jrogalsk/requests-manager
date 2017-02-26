package com.jrogalsk.requestsmanager.core.business.domain.request;

import com.jrogalsk.requestsmanager.core.business.domain.statetransition.State;
import com.jrogalsk.requestsmanager.core.business.domain.statetransition.StateTransitionHistoryEntry;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class RequestTest {
    @Test
    public void whenInitialized_doesNotHaveId() {
        assertNull(new Request("moo", "boo").getId());
    }

    @Test
         public void whenInitialized_hasStateSetToCreated() {
        assertThat(new Request("moo", "boo").getState(), is(State.CREATED));
    }

    @Test
    public void whenInitialized_doesNotHaveStatusChangeJustification() {
        assertThat(new Request("moo", "boo").getStatusChangeJustification(), is(""));
    }

    @Test
    public void updateStatusOnStateTransition() {
        Request request = new Request("moo", "boo");
        request.addStateTransitionHistoryEntry(new StateTransitionHistoryEntry(State.CREATED, State.DELETED, ""));

        assertThat(request.getState(), is(State.DELETED));
    }

    @Test
    public void updateStatusChangeJustificationOnStateTransition() {
        Request request = new Request("moo", "boo");
        request.addStateTransitionHistoryEntry(new StateTransitionHistoryEntry(State.CREATED, State.DELETED, "Very important reason"));

        assertThat(request.getStatusChangeJustification(), is("Very important reason"));
    }
}
