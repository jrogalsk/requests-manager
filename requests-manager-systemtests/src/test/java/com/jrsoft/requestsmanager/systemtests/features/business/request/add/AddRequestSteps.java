package com.jrsoft.requestsmanager.systemtests.features.business.request.add;

import com.jrsoft.requestsmanager.systemtests.features.business.request.common.RequestFeatureSteps;
import io.restassured.response.Response;

import static org.hamcrest.core.Is.is;

public class AddRequestSteps extends RequestFeatureSteps {
    private String title;
    private String content;
    private String createdRequestId;

    private Response response;

    public void setNewRequestMetadata(String aTitle, String aContent) {
        this.setTitle(aTitle);
        this.setContent(aContent);
    }

    public void addNewRequestToTheSystem() {
        Response response = this.requestResourceDriver()
                .addRequestWith(this.title(), this.content());
        this.setResponse(response);
    }

    public void verifyThatRequestWasAddedToTheSystem() {
        this.response().then()
                .statusCode(is(201));
        this.setCreatedRequestId(this.extractNewRequestId());
    }

    public void verifyThatRequestWasNotAddedToTheSystem() {
        this.response().then()
                .statusCode(is(400));
    }

    private String extractNewRequestId() {
        String location = this.response().header("Location");

        return location.substring(location.lastIndexOf('/'));
    }

    private void setTitle(String aTitle) {
        this.title = aTitle;
    }

    private void setContent(String aContent) {
        this.content = aContent;
    }

    public String title() {
        return this.title;
    }

    public String content() {
        return this.content;
    }

    private void setResponse(Response aResponse) {
        this.response = aResponse;
    }

    private Response response() {
        return this.response;
    }

    private void setCreatedRequestId(String aRequestId) {
        this.createdRequestId = aRequestId;
    }

    public String getCreatedRequestId() {
        return createdRequestId;
    }
}
