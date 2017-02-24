package com.jrsoft.requestsmanager.systemtests.features.monitoring.ping;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.core.Is.is;

public class PingFeatureSteps {
    private final PingResourceDriver pingResourceDriver = new PingResourceDriver();

    private Response pingResponse;
    private int expectedReturnCode;

    @Step
    public void pingSystem() {
        this.setPingResponse(this.pingResourceDriver().callPingResource());
    }

    @Step
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
