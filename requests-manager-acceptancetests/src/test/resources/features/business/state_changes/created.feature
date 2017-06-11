Feature: Change state of created request

  Users can verify or delete created requests

  Background:
    Given Jim has new request with state 'CREATED'

  Scenario Outline: Change state with accepted actions
    When he performs '<performed_action>' action on this request with '<justification>' justification
    Then his action is successful
    And request has state set to '<expected_state>'
  Examples:
    | performed_action | expected_state | justification |
    | VERIFY           | VERIFIED       |               |
    | DELETE           | DELETED        | dummy         |

  Scenario Outline: Fail to change state with not accepted actions
    When he performs '<performed_action>' action on this request
    Then his action is rejected
    And request has state set to 'CREATED'
  Examples:
    | performed_action |
    | CREATE           |
    | REJECT           |
    | ACCEPT           |
    | PUBLISH          |
    | DUMMY            |
