package com.jrsoft.requestsmanager.systemtests.features.business.transition;

import com.jrsoft.requestsmanager.systemtests.features.business.request.add.AddRequestSteps;
import com.jrsoft.requestsmanager.systemtests.features.business.request.viewdetails.ViewDetailsSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;

public class RequestStateTransitionFeatureDefinition {
    private AddRequestSteps addRequestSteps = new AddRequestSteps();
    private ViewDetailsSteps viewDetailsSteps = new ViewDetailsSteps();
    private TransitionRequestStateSteps transitionRequestStateSteps = new TransitionRequestStateSteps();

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

    @Given("^he performs '(.*)' action on this request$")
    public void he_performs_operation_on_this_request(String anAction) {
        this.transitionRequestStatusSteps()
                .forRequestWithId(this.getTestedRequestId())
                .invokeStateChangeAction(anAction);
    }

    @Given("he performs '(.*)' action on this request with '(.*)' justification")
    public void he_performs_operation_on_this_request_with_justification(String anAction, String justification) {
        this.transitionRequestStatusSteps()
                .forRequestWithId(this.getTestedRequestId())
                .invokeStateChangeActionWithJustification(anAction, justification);
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

    @Then("^this request has history entries for states in following order: (.*)$")
    public void this_request_has_history_entries_for_states(List<String> states) {
        this.viewDetailsSteps()
                .fetchDetailsOfRequestWithId(this.getTestedRequestId())
                .verifyThatRequestExists()
                .andItHasStatesHistoryFor(states);
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

    private TransitionRequestStateSteps transitionRequestStatusSteps() {
        return this.transitionRequestStateSteps;
    }
}
