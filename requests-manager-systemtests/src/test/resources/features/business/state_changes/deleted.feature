Feature: Change state of deleted request

  Users can not change state of deleted request

  Background:
    Given Jim has new request with state 'CREATED'
    When he performs 'DELETE' action on this request with 'dummy' justification
    And request has state set to 'DELETED'

  Scenario Outline: Fail to change state with not accepted actions
    When he performs '<performed_action>' action on this request
    Then his action is rejected
    And request has state set to 'DELETED'
  Examples:
    | performed_action |
    | VERIFY           |
    | DELETE           |
    | CREATE           |
    | REJECT           |
    | ACCEPT           |
    | PUBLISH          |
    | DUMMY            |
