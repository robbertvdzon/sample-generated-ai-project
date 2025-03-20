package com.example.demo

import org.junit.jupiter.api.Test
import static org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
@SpringBootTest
class UserServiceTest {
    @Autowired
    lateinit var userService: UserService

    @Test
    fun testAddUsername() {
        userService.addUsername("testUser")
        assertThat(userService.getAllUsernames()).contains("testUser")
    }
}
