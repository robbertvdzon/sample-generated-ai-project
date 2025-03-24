package com.example.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserSteps {
    @Autowired
    private lateinit var userController: UserController

    @Autowired
    private lateinit var deleteUserController: DeleteUserController

    @Given("the list of users is empty")
    fun the_list_of_users_is_empty() {
        // Implementation for clearing user list if necessary
    }

    @When("a user with username {string} is added")
    fun a_user_with_username_is_added(username: String) {
        val response = userController.addUser(username, null)
        assertEquals(200, response.statusCode.value())
    }

    @Then("the list of users contains the user with username {string}")
    fun the_list_of_users_contains_the_user_with_username(username: String) {
        val usernames = userController.getAllUsernames()
        assertTrue(usernames.contains(username))
    }

    @When("a user with username {string} is deleted")
    fun a_user_with_username_is_deleted(username: String) {
        val response = deleteUserController.deleteUser(username)
        assertEquals(200, response.statusCode.value())
    }
}
