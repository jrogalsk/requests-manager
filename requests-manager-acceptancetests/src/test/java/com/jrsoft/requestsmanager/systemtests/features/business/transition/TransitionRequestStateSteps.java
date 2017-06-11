package com.jrsoft.requestsmanager.systemtests.features.business.transition;

import com.jrsoft.requestsmanager.systemtests.features.business.commons.RequestsManagerFeatureSteps;
import io.restassured.response.Response;

public class TransitionRequestStateSteps extends RequestsManagerFeatureSteps {
    private String requestId;

    private Response response;

    public TransitionRequestStateSteps forRequestWithId(String aRequestId) {
        this.setRequestId(aRequestId);

        return this;
    }

    public TransitionRequestStateSteps invokeStateChangeAction(String aChangeStateAction) {
        Response response = this.requestManagerResourceDriver()
                .invokeChangeStateAction(this.requestId(), aChangeStateAction, "");
        this.setResponse(response);

        return this;
    }

    public TransitionRequestStateSteps invokeStateChangeActionWithJustification(String aChangeStateAction, String justification) {
        Response response = this.requestManagerResourceDriver()
                .invokeChangeStateAction(this.requestId(), aChangeStateAction, justification);
        this.setResponse(response);

        return this;
    }

    public TransitionRequestStateSteps verifyThatTransitionWasSuccessful() {
        this.response().then()
                .statusCode(202);

        return this;
    }

    public TransitionRequestStateSteps verifyThatTransitionWasRejected() {
        this.response().then()
                .statusCode(400);

        return this;
    }

    private void setResponse(Response aResponse) {
        this.response = aResponse;
    }

    private Response response() {
        return this.response;
    }

    private void setRequestId(String aRequestId) {
        this.requestId = aRequestId;
    }

    private String requestId() {
        return this.requestId;
    }
}
