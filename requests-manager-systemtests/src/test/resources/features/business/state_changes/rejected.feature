Feature: Change state of rejected request

  Users can not change state of rejected request

  Background:
    Given Jim has new request with state 'CREATED'
    When he performs 'VERIFY' action on this request
    And he performs 'REJECT' action on this request
    And request has state set to 'REJECTED'

  Scenario Outline: Fail to change state with not accepted actions
    When he performs '<performed_action>' action on this request
    Then his action is rejected
    And request has state set to 'REJECTED'
  Examples:
    | performed_action |
    | VERIFY           |
    | DELETE           |
    | CREATE           |
    | REJECT           |
    | ACCEPT           |
    | PUBLISH          |
    | DUMMY            |
