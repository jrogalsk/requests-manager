package com.jrsoft.requestsmanager.systemtests.features.monitoring.ping;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PingFeatureDefinition {
    private PingSteps pingSteps = new PingSteps();

    @Given("Jim wants to verify that that system is up and running")
    public void jim_wants_to_verify_that_system_is_up_and_running() throws Throwable {
        this.pingFeatureSteps().setExpectedReturnCode(200);
    }

    @When("he calls Ping resource")
    public void he_calls_ping_resource() throws Throwable {
        this.pingFeatureSteps().pingSystem();
    }

    @Then("he receives '(.*)' message returned by the system")
    public void he_receives_message_returned_by_the_system(String aMessage) throws Throwable {
        this.pingFeatureSteps().verifyThatRespondedWith(aMessage);
    }

    private PingSteps pingFeatureSteps() {
        return this.pingSteps;
    }
}
