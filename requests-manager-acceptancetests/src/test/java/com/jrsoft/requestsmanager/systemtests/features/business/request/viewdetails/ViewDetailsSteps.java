package com.jrsoft.requestsmanager.systemtests.features.business.request.viewdetails;

import com.jrsoft.requestsmanager.systemtests.features.business.commons.RequestsManagerFeatureSteps;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ViewDetailsSteps extends RequestsManagerFeatureSteps {
    private Response response;

    public ViewDetailsSteps fetchDetailsOfRequestWithId(String aRequestId) {
        Response response = this.requestManagerResourceDriver()
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

    public ViewDetailsSteps andItHasStatesHistoryFor(List<String> expectedStatesHistory) {
        List<Map<String, String>> mappedStateChangesHistory = response().getBody().path("stateTransitionHistory");
        List<String> actualStatesHistory = mappedStateChangesHistory
                .stream()
                .map(entry -> entry.get("to"))
                .collect(Collectors.toList());

        assertThat(actualStatesHistory, is(expectedStatesHistory));

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
