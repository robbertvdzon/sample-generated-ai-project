// Import necessary libraries
import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserIntegrationTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    // Feature: Add a new user
    @Given("a user is in the system")
    fun a_user_is_in_the_system() {}

    @When("I add a new user with username 'john_doe'")
    fun i_add_a_new_user_with_username_john_doe() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/addUser?username=john_doe")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Then("the user 'john_doe' is added to the system")
    fun the_user_john_doe_is_added_to_the_system() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("")
                .accept(MediaType.TEXT_HTML)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(contains("john_doe")))
    }

    // Feature: Delete a user
    @Given("a user 'jane_doe' is in the system")
    fun a_user_jane_doe_is_in_the_system() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/addUser?username=jane_doe")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @When("I delete the user 'jane_doe'")
    fun i_delete_the_user_jane_doe() {
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/deleteUser/jane_doe")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Then("the user 'jane_doe' is removed from the system")
    fun the_user_jane_doe_is_removed_from_the_system() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("")
                .accept(MediaType.TEXT_HTML)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(not(contains("jane_doe"))))
    }
}