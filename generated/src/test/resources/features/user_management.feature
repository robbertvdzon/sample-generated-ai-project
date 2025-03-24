Feature: User Management
  In order to manage users effectively
  As a system administrator
  I want to be able to add and delete users

  Scenario Outline: Adding a user
    Given the list of users is empty
    When a user with username <username> is added
    Then the list of users contains the user with username <username>
    Examples:
      | username |
      | testuser1 |
      | testuser2 |

  Scenario Outline: Deleting a user
    Given the list of users contains the user with username <username>
    When a user with username <username> is deleted
    Then the list of users does not contain the user with username <username>
    Examples:
      | username |
      | testuser1 |
      | testuser2 |
