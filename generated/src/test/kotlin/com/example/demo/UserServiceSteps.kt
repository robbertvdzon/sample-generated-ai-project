package com.example.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.web.server.ResponseStatusException

class UserServiceSteps(
    private val userService: UserService) {

    private val johnDoe = "John Doe"
    private var errorMessage: String? = null

    @Given("the user {string} exists in the system")
    fun `the user John Doe exists in the system`(username: String) {
        userService.addUsername(username)
    }

    @When("the administrator deletes user {string}")
    fun `the administrator deletes user John Doe`(username: String) {
        try {
            userService.deleteUsername(username)
        } catch (e: Exception) {
            errorMessage = e.message
        }
    }

    @Then("user {string} is removed from the system")
    fun `user John Doe is removed from the system`(username: String) {
        Assertions.assertFalse(userService.getAllUsernames().contains(username))
    }

    @Given("the user {string} does not exist in the system")
    fun `the user Jane Doe does not exist in the system`(username: String) {}

    @When("the administrator tries to delete user {string}")
    fun `the administrator tries to delete user Jane Doe`(username: String) {
        try {
            userService.deleteUsername(username)
        } catch (e: ResponseStatusException) {
            errorMessage = e.reason
        }
    }

    @Then("the system displays an error message {string}")
    fun `the system displays an error message User not found`(errorMessageExpected: String) {
        assertEquals(errorMessageExpected, errorMessage)
    }

    @Given("there are no users in the system")
    fun `there are no users in the system`() {
        userService.getAllUsernames().forEach { userService.deleteUsername(it) }
    }

    @When("the administrator attempts to delete any user")
    fun `the administrator attempts to delete any user`() {
        try {
            userService.deleteUsername(johnDoe)
        } catch (e: ResponseStatusException) {
            errorMessage = e.reason
        }
    }

    @Then("the system informs that there are no users to delete")
    fun `the system informs that there are no users to delete`() {
        assertEquals("User not found", errorMessage)
    }
}
