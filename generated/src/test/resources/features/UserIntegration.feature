# User Integration Feature
Feature: Test user integration
  As a developer,
  I want to ensure that the user management system works correctly
  So that users can be added and displayed

  Scenario Outline: Add a new user and verify it is listed
    Given the system is running
    When a new user is added via POST /addUser with username "<username>"
    Then the new user is displayed on the main page

    Examples:
      | username |
      | Alice  |
      | Bob    |
