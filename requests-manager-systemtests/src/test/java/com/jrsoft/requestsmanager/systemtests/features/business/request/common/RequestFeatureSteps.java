package com.jrsoft.requestsmanager.systemtests.features.business.request.common;

public abstract class RequestFeatureSteps {
    private RequestResourceDriver requestResourceDriver = new RequestResourceDriver();

    protected RequestResourceDriver requestResourceDriver() {
        return this.requestResourceDriver;
    }
}
