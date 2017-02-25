package com.jrsoft.requestsmanager.systemtests.features.business.request.add;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddRequestFeatureDefinition {
    private AddRequestSteps addRequestSteps = new AddRequestSteps();

    @Given("Jim has new request with title '(.*)' and content '(.*)'")
    public void jim_has_new_request_with_title_and_content(String aTitle, String aContent) {
        this.addRequestSteps()
                .setNewRequestMetadata(aTitle, aContent);
    }

    @When("he adds his request to the system")
    public void he_adds_his_request_to_the_system() {
        this.addRequestSteps()
                .addNewRequestToTheSystem();
    }

    @Then("this request is added to the system")
    public void this_request_is_added_to_the_system() {
        this.addRequestSteps()
                .verifyThatRequestWasAddedToTheSystem();
    }

    @Then("Jim can view details of his new request")
    public void jim_can_view_details_of_his_new_request() {
        this.addRequestSteps().verifyThatCanViewDetailsOfCreatedRequest();
    }

    @Then("this request is not added to the system")
    public void this_request_is_not_added_to_the_system() {
        this.addRequestSteps()
                .verifyThatRequestWasNotAddedToTheSystem();
    }

    private AddRequestSteps addRequestSteps() {
        return this.addRequestSteps;
    }
}
