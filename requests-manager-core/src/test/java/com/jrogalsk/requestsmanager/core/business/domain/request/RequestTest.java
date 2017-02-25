package com.jrogalsk.requestsmanager.core.business.domain.request;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class RequestTest {

    @Test
    public void whenInitializedDoesNotHaveId() {
        assertNull(new Request("moo", "boo").getId());
    }

}
