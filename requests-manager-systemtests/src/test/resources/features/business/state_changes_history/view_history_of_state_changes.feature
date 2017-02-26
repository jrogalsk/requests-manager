Feature: View history of request state changes

  Users can view history of request state changes

  Scenario: Change state with accepted actions
    Given Jim has new request with state 'CREATED'
    When he performs 'VERIFY' action on this request
    And he performs 'ACCEPT' action on this request
    And he performs 'PUBLISH' action on this request
    Then this request has history entries for states in following order: CREATED, VERIFIED, ACCEPTED, PUBLISHED
