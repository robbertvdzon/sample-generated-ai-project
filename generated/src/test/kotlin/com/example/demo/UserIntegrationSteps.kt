package com.example.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationSteps {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Given("the system is running")
    fun the_system_is_running() {}

    @When("a new user is added via POST /addUser with username "<username>"
    fun a_new_user_is_added_via_post_adduser_with_username(username: String) {
        mockMvc.perform(post("/addUser?username=$username"))
            .andExpect(status().isOk)
    }

    @Then("the new user is displayed on the main page")
    fun the_new_user_is_displayed_on_the_main_page() {
        mockMvc.perform(get("")
            .accept(MediaType.TEXT_HTML_VALUE))
            .andExpect(status().isOk)
            .andExpect(content().string.contains("$username"))
    }
}
