package com.jrogalsk.requestsmanager.core.integration.monitoring;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PingResourceTest {
    @Test
    public void respondToPingRequest() {
        assertThat(new PingResource().getPingResponse(), is("pong"));
    }
}
