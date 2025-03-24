Feature: User Management
  Scenario Outline: Adding a new user
    Given There are no users in the system
    When A user adds <username>
    Then The user should see <username> in the list of users

    Examples:
      | username |
      | John     |
      | Jane     |