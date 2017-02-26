package com.jrsoft.requestsmanager.systemtests.features.business.commons;

import com.jrsoft.requestsmanager.systemtests.RESTResourceDriver;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestsManagerResourceDriver extends RESTResourceDriver {
    private static final String RELATIVE_PATH = "request";

    public Response addRequestWith(String aTitle, String aContent) {
        return given()
                .param("title", aTitle)
                .param("content", aContent)
                .when()
                .post(this.resourcePath());
    }

    public Response fetchRequestWithId(String aRequestId) {
        return given()
                .get(String.format("%s%s", this.resourcePath(), aRequestId));
    }

    private String resourcePath() {
        return this.fullPathWith(RequestsManagerResourceDriver.RELATIVE_PATH);
    }

    public Response invokeChangeStateAction(String aRequestId, String aChangeStateAction, String justification) {
        return given()
                .param("action", aChangeStateAction)
                .param("actionJustification", justification)
                .post(String.format("%s%s", this.resourcePath(), aRequestId));
    }
}
