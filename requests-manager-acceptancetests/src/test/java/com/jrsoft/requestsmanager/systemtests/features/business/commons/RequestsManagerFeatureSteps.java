package com.jrsoft.requestsmanager.systemtests.features.business.commons;

public abstract class RequestsManagerFeatureSteps {
    private RequestsManagerResourceDriver requestResourceDriver = new RequestsManagerResourceDriver();

    protected RequestsManagerResourceDriver requestManagerResourceDriver() {
        return this.requestResourceDriver;
    }
}
