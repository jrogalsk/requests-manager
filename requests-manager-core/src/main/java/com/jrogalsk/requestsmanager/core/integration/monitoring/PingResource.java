package com.jrogalsk.requestsmanager.core.integration.monitoring;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/ping")
public class PingResource {
    @GET
    public String getPingResponse() {
        return "pong";
    }
}
