package com.jrsoft.requestsmanager.systemtests.features.business.request.common;

import com.jrsoft.requestsmanager.systemtests.RESTResourceDriver;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResourceDriver extends RESTResourceDriver {
    private static final String RELATIVE_PATH = "request";

    public Response addRequestWith(String aTitle, String aContent) {
        return given()
                .param("title", aTitle)
                .param("content", aContent)
                .when()
                .post(this.resourcePath());
    }

    public Response fetchRequestWithId(String id) {
        return given()
                .get(String.format("%s%s", this.resourcePath(), id));
    }

    private String resourcePath() {
        return this.fullPathWith(RequestResourceDriver.RELATIVE_PATH);
    }
}
