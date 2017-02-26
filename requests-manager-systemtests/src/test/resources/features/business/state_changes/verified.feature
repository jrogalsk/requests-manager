Feature: Change state of verified request

  Users can reject or accept verified request

  Background:
    Given Jim has new request with state 'CREATED'
    When he performs 'VERIFY' action on this request
    And request has state set to 'VERIFIED'

  Scenario Outline: Change state with accepted actions
    When he performs '<performed_action>' action on this request with '<justification>' justification
    Then his action is successful
    And request has state set to '<expected_state>'
  Examples:
    | performed_action | expected_state | justification |
    | REJECT           | REJECTED       | dummy         |
    | ACCEPT           | ACCEPTED       |               |

  Scenario Outline: Fail to change state with not accepted actions
    When he performs '<performed_action>' action on this request
    Then his action is rejected
    And request has state set to 'VERIFIED'
  Examples:
    | performed_action |
    | VERIFY           |
    | DELETE           |
    | CREATE           |
    | PUBLISH          |
    | DUMMY            |
