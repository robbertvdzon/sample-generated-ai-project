Feature: Remove a User

  Scenario: Removing an existing user
    Given the user "John Doe" exists in the system
    When the administrator deletes user "John Doe"
    Then user "John Doe" is removed from the system

  Scenario: Attempting to remove a non-existent user
    Given the user "Jane Doe" does not exist in the system
    When the administrator tries to delete user "Jane Doe"
    Then the system displays an error message "User not found"

  Scenario: Trying to delete a user when the user list is empty
    Given there are no users in the system
    When the administrator attempts to delete any user
    Then the system informs that there are no users to delete