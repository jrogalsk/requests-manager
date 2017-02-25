package com.jrsoft.requestsmanager.systemtests.features.business.request.add;

import com.jrsoft.requestsmanager.systemtests.features.business.request.common.RequestResourceDriver;
import io.restassured.response.Response;

import static org.hamcrest.core.Is.is;

public class AddRequestSteps {
    private RequestResourceDriver requestResourceDriver = new RequestResourceDriver();

    private String title;
    private String content;

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
    }

    public void verifyThatRequestWasNotAddedToTheSystem() {
        this.response().then()
                .statusCode(is(400));
    }

    public void verifyThatCanViewDetailsOfCreatedRequest() {
        this.requestResourceDriver()
                .fetchRequestWithId(this.extractNewRequestId())
                .then().statusCode(200);
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

    private RequestResourceDriver requestResourceDriver() {
        return this.requestResourceDriver;
    }

    private String title() {
        return this.title;
    }

    private String content() {
        return this.content;
    }

    private void setResponse(Response aResponse) {
        this.response = aResponse;
    }

    private Response response() {
        return this.response;
    }

}
