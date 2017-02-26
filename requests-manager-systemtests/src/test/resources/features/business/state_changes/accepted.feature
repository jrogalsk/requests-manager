Feature: Change state of accepted request

  Users can publish or reject created requests

  Background:
    Given Jim has new request with state 'CREATED'
    When he performs 'VERIFY' action on this request
    And he performs 'ACCEPT' action on this request
    And request has state set to 'ACCEPTED'

  Scenario Outline: Change state with accepted actions
    When he performs '<performed_action>' action on this request
    Then his action is successful
    And request has state set to '<expected_state>'
  Examples:
    | performed_action | expected_state |
    | PUBLISH          | PUBLISHED      |
    | REJECT           | REJECTED       |

  Scenario Outline: Fail to change state with not accepted actions
    When he performs '<performed_action>' action on this request
    Then his action is rejected
    And request has state set to 'ACCEPTED'
  Examples:
    | performed_action |
    | VERIFY           |
    | DELETE           |
    | CREATE           |
    | ACCEPT           |
    | DUMMY            |
