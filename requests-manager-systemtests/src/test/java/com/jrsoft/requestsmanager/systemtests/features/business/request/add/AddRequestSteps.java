package com.jrsoft.requestsmanager.systemtests.features.business.request.add;

import com.jrsoft.requestsmanager.systemtests.features.business.commons.RequestsManagerFeatureSteps;
import io.restassured.response.Response;

import static org.hamcrest.core.Is.is;

public class AddRequestSteps extends RequestsManagerFeatureSteps {
    private String title;
    private String content;
    private String createdRequestId;

    private Response response;

    public AddRequestSteps setNewRequestMetadata(String aTitle, String aContent) {
        this.setTitle(aTitle);
        this.setContent(aContent);

        return this;
    }

    public AddRequestSteps addNewRequestToTheSystem() {
        Response response = this.requestManagerResourceDriver()
                .addRequestWith(this.title(), this.content());
        this.setResponse(response);

        return this;
    }

    public AddRequestSteps verifyThatRequestWasAddedToTheSystem() {
        this.response().then()
                .statusCode(is(201));
        this.setCreatedRequestId(this.extractNewRequestId());

        return this;
    }

    public AddRequestSteps verifyThatRequestWasNotAddedToTheSystem() {
        this.response().then()
                .statusCode(is(400));

        return this;
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
