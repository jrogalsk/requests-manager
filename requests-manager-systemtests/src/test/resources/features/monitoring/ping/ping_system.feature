Feature: Ping System

  Ping is the simplest method which allows to verify if the system is up and running.
  It has no business value and shall be used for basic monitoring.

  Scenario: Single call to Ping resource to verify that system is up and running
    Given Jim wants to verify that that system is up and running
    When he calls Ping resource
    Then he receives 'pong' message returned by the system
