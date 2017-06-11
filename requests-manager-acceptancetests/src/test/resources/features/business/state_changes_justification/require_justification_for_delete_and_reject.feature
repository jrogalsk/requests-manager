Feature: Provide justification for actions

  Users shall provide justification when they reject or delete requests

  Scenario: User provides justification for deleting request
    Given Jim has new request with state 'CREATED'
    When he performs 'DELETE' action on this request with 'dummy' justification
    And request has state set to 'DELETED'

  Scenario: User does not provide justification for deleting request
    Given Jim has new request with state 'CREATED'
    When he performs 'DELETE' action on this request
    Then his action is rejected
    And request has state set to 'CREATED'

  Scenario: User provides justification for rejecting request
    Given Jim has new request with state 'CREATED'
    When he performs 'VERIFY' action on this request
    And he performs 'REJECT' action on this request with 'dummy' justification
    And request has state set to 'REJECTED'

  Scenario: User does not provide justification for rejecting request
    Given Jim has new request with state 'CREATED'
    When he performs 'VERIFY' action on this request
    And he performs 'REJECT' action on this request
    Then his action is rejected
    And request has state set to 'VERIFIED'
