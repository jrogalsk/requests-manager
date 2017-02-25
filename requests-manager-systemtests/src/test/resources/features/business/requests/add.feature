Feature: Add new request

  Users can add new requests to the system.
  New requests shall have title and content.

  Scenario: Add new request when name and content are provided
    Given Jim has new request with title 'New request' and content 'New content'
    When he adds his request to the system
    Then this request is added to the system
    And Jim can view details of his new request with state set to 'CREATED'

  Scenario Outline: Fail to add new request when name or content is not provided
    Given Jim has new request with title '<title>' and content '<content>'
    When he adds his request to the system
    Then this request is not added to the system
    And Jim can not view details of his new request

    Examples:
      | title       | content     |
      | New request |             |
      |             | New content |
      |             |             |
