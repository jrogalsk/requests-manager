package com.jrsoft.requestsmanager.systemtests.features.business.transition;

import com.jrsoft.requestsmanager.systemtests.features.business.request.add.AddRequestSteps;
import com.jrsoft.requestsmanager.systemtests.features.business.request.viewdetails.ViewDetailsSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class RequestStatusTransitionFeatureDefinition {
    private AddRequestSteps addRequestSteps = new AddRequestSteps();
    private ViewDetailsSteps viewDetailsSteps = new ViewDetailsSteps();
    private TransitionRequestStatusSteps transitionRequestStatusSteps = new TransitionRequestStatusSteps();

    @Given("Jim has new request with state '(.*)'")
    public void jim_has_new_request_with_state(String aState) {
        this.addRequestSteps()
                .setNewRequestMetadata("Transition test request", "Important content")
                .addNewRequestToTheSystem()
                .verifyThatRequestWasAddedToTheSystem();

        this.viewDetailsSteps()
                .fetchDetailsOfRequestWithId(this.getTestedRequestId())
                .verifyThatRequestExists()
                .andItsStatusIs(aState);
    }

    @Given("he performs '(.*)' action on this request")
    public void he_performs_operation_on_this_request(String anAction) {
        this.transitionRequestStatusSteps()
                .forRequestWithId(this.getTestedRequestId())
                .invokeStateChangeAction(anAction);
    }

    @Then("request has state set to '(.*)'")
    public void request_has_state_set_to(String expectedState) {
        this.viewDetailsSteps()
                .fetchDetailsOfRequestWithId(this.getTestedRequestId())
                .verifyThatRequestExists()
                .andItsStatusIs(expectedState);
    }

    @Then("his action is rejected")
    public void his_action_is_rejected() {
        this.transitionRequestStatusSteps()
                .verifyThatTransitionWasRejected();
    }

    @Then("his action is successful")
    public void his_action_is_successful() {
        this.transitionRequestStatusSteps()
                .verifyThatTransitionWasSuccessful();
    }

    private String getTestedRequestId() {
        return this.addRequestSteps().getCreatedRequestId();
    }

    private AddRequestSteps addRequestSteps() {
        return this.addRequestSteps;
    }

    private ViewDetailsSteps viewDetailsSteps() {
        return this.viewDetailsSteps;
    }

    private TransitionRequestStatusSteps transitionRequestStatusSteps() {
        return this.transitionRequestStatusSteps;
    }
}
