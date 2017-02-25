package com.jrsoft.requestsmanager.systemtests.features.business.request.add;

import com.jrsoft.requestsmanager.systemtests.features.business.request.viewdetails.ViewDetailsSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddRequestFeatureDefinition {
    private AddRequestSteps addRequestSteps = new AddRequestSteps();
    private ViewDetailsSteps viewDetailsSteps = new ViewDetailsSteps();

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

    @Then("Jim can view details of his new request with state set to '(.*)'")
    public void jim_can_view_details_of_his_new_request(String expectedStatus) {
        String createdRequestId = this.addRequestSteps().getCreatedRequestId();
        String expectedTitle = this.addRequestSteps().title();
        String expectedContent = this.addRequestSteps().content();

        this.viewDetailsSteps()
                .fetchDetailsOfRequestWithId(createdRequestId)
                .verifyThatRequestExists()
                .andItsTitleIs(expectedTitle)
                .andItsContentIs(expectedContent)
                .andItsStatusIs(expectedStatus);
    }

    @Then("this request is not added to the system")
    public void this_request_is_not_added_to_the_system() {
        this.addRequestSteps()
                .verifyThatRequestWasNotAddedToTheSystem();
    }

    @Then("Jim can not view details of his new request")
    public void jim_can_not_see_details_of_his_new_request() {
        String createdRequestId = this.addRequestSteps().getCreatedRequestId();

        this.viewDetailsSteps()
                .fetchDetailsOfRequestWithId(createdRequestId)
                .verifyThatDoesNotExist();
    }

    private AddRequestSteps addRequestSteps() {
        return this.addRequestSteps;
    }

    private ViewDetailsSteps viewDetailsSteps() {
        return this.viewDetailsSteps;
    }
}
