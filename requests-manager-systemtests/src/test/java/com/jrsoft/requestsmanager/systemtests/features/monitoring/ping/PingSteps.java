package com.jrsoft.requestsmanager.systemtests.features.monitoring.ping;

import io.restassured.response.Response;

import static org.hamcrest.core.Is.is;

public class PingSteps {
    private final PingResourceDriver pingResourceDriver = new PingResourceDriver();

    private Response pingResponse;
    private int expectedReturnCode;

    public void pingSystem() {
        this.setPingResponse(this.pingResourceDriver().callPingResource());
    }

    public void verifyThatRespondedWith(String aText) {
        this.pingResponse().then()
                .statusCode(is(this.expectedReturnCode()))
                .assertThat()
                .body(is(aText));
    }

    private PingResourceDriver pingResourceDriver() {
        return this.pingResourceDriver;
    }

    private Response pingResponse() {
        return this.pingResponse;
    }

    private void setPingResponse(Response aPingResponse) {
        this.pingResponse = aPingResponse;
    }

    private int expectedReturnCode() {
        return this.expectedReturnCode;
    }

    public void setExpectedReturnCode(int aCode) {
        this.expectedReturnCode = aCode;
    }
}
