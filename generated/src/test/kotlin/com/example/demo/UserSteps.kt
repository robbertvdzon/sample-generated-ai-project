package com.example.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Assert.assertEquals
import java.util.concurrent.atomic.AtomicReference

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSteps {
    @Autowired
    private lateinit var webClient: WebClient

    @Autowired
    private lateinit var userService: UserService

    private val lastAddedUsername = AtomicReference<String>()

    @Given("There are no users in the system")
    fun thereAreNoUsersInTheSystem() {
        userService.clearUsernames()
    }

    @When("A user adds {string}")
    fun aUserAdds(username: String) {
        lastAddedUsername.set(username)
        webClient.post().uri("/addUser").bodyValue(mapOf("username" to username)).retrieve().toBodilessEntity().block()
    }

    @Then("The user should see {string} in the list of users")
    fun theUserShouldSeeInTheListOfUsers(username: String) {
        val usernames = webClient.get().uri("").retrieve().bodyToMono<List<String>>().block()!!
        assertEquals(listOf(lastAddedUsername.get()), usernames)
    }
}