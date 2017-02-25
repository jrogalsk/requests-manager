package com.jrogalsk.requestsmanager.core.business.domain.request;

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
    public void whenInitialized_hasStateSetAsCreated() {
        assertThat(new Request("moo", "boo").getState(), is(RequestState.CREATED));
    }
}
