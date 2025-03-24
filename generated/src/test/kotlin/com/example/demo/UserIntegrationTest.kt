package com.example.demo

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import static org.assertj.core.api.Assertions.assertThat
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Given("the system is running")
    fun the_system_is_running() {}

    @When("a new user is added via POST /addUser")
    fun a_new_user_is_added_via_post_adduser(username: String) {
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
