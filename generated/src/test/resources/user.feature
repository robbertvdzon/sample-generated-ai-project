// Define cucumber feature file
Feature: User Management
  Scenario: Add a new user
    Given a user is in the system
    When I add a new user with username 'john_doe'
    Then the user 'john_doe' is added to the system

  Scenario: Delete an existing user
    Given a user 'jane_doe' is in the system
    When I delete the user 'jane_doe'
    Then the user 'jane_doe' is removed from the system