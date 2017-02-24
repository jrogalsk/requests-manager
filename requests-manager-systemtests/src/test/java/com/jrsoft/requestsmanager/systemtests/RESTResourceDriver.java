package com.jrsoft.requestsmanager.systemtests;

public class RESTResourceDriver {
    private static final String RESOURCES_URL =
            String.format("%s/resources/", TestedSystemConfiguration.REQUESTS_MANAGER_SYSTEM_BASE_URL);

    protected String fullPathWith(final String relativePath) {
        return String.format("%s%s", RESTResourceDriver.RESOURCES_URL, relativePath);
    }
}
