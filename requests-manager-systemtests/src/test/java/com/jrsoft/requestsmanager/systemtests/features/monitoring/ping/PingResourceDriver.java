package com.jrsoft.requestsmanager.systemtests.features.monitoring.ping;

import com.jrsoft.requestsmanager.systemtests.RESTResourceDriver;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PingResourceDriver extends RESTResourceDriver {
    private static final String RELATIVE_PATH = "ping";

    public Response callPingResource() {
        return given()
                .contentType(ContentType.TEXT)
                .get(this.fullPathWith(PingResourceDriver.RELATIVE_PATH));
    }
}
