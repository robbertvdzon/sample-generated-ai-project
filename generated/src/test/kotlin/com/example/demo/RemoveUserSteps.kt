package com.example.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert.assertFalse
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

class RemoveUserSteps {
    @Autowired
    private lateinit var userService: UserService

    private var exception: Exception? = null

    @Given("the user {string} exists in the system")
    fun `the user exists in the system`(username: String) {
        userService.addUsername(username)
    }

    @When("the administrator deletes user {string}")
    fun `the administrator deletes user`(username: String) {
        try {
            userService.deleteUsername(username)
        } catch (e: Exception) {
            exception = e
        }
    }

    @Then("user {string} is removed from the system")
    fun `user is removed from the system`(username: String) {
        assertFalse(userService.getAllUsernames().contains(username))
    }

    @Given("the user {string} does not exist in the system")
    fun `the user does not exist in the system`(username: String) {}

    @When("the administrator tries to delete user {string}")
    fun `the administrator tries to delete user`(username: String) {
        try {
            userService.deleteUsername(username)
        } catch (e: Exception) {
            exception = e
        }
    }

    @Then("the system displays an error message {string}")
    fun `the system displays an error message`(errorMessage: String) {
        assertEquals(errorMessage, exception?.message)
    }

    @Given("there are no users in the system")
    fun `there are no users in the system`() {}

    @When("the administrator attempts to delete any user")
    fun `the administrator attempts to delete any user`() {
        try {
            userService.deleteUsername("NonExistentUser")
        } catch (e: Exception) {
            exception = e
        }
    }

    @Then("the system informs that there are no users to delete")
    fun `the system informs that there are no users to delete`() {
        assertEquals("User not found", exception?.message)
    }
}