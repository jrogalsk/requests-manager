package com.jrsoft.requestsmanager.systemtests.features.business.request.viewdetails;

import com.jrsoft.requestsmanager.systemtests.features.business.request.common.RequestFeatureSteps;
import io.restassured.response.Response;

import static org.hamcrest.core.Is.is;

public class ViewDetailsSteps extends RequestFeatureSteps {
    private Response response;

    public ViewDetailsSteps fetchDetailsOfRequestWithId(String aRequestId) {
        Response response = this.requestResourceDriver()
                .fetchRequestWithId(aRequestId);
        this.setResponse(response);

        return this;
    }

    public void verifyThatDoesNotExist() {
        this.verifyThatResponseHasStatusCode(404);
    }

    public ViewDetailsSteps verifyThatRequestExists() {
        this.verifyThatResponseHasStatusCode(200);

        return this;
    }

    public ViewDetailsSteps andItsTitleIs(String aTitle) {
        this.verifyThatResponseContains("title", aTitle);

        return this;
    }

    public ViewDetailsSteps andItsContentIs(String aContent) {
        this.verifyThatResponseContains("content", aContent);

        return this;
    }

    public ViewDetailsSteps andItsStatusIs(String aStatus) {
        this.verifyThatResponseContains("state", aStatus);

        return this;
    }

    private void verifyThatResponseContains(String anAttribute, String value) {
        this.response()
                .then()
                .body(anAttribute, is(value));
    }

    private void verifyThatResponseHasStatusCode(int aStatusCode) {
        this.response()
                .then()
                .statusCode(aStatusCode);
    }

    private void setResponse(Response aResponse) {
        this.response = aResponse;
    }

    private Response response() {
        return this.response;
    }
}
