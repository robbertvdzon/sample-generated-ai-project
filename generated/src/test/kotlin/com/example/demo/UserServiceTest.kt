package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserServiceTest {

    private val userService = UserService()

    @Test
    fun `should add and retrieve usernames`() {
        userService.addUsername("john")
        userService.addUsername("jane")

        val users = userService.getAllUsernames()

        assertEquals(2, users.size)
        assertTrue(users.contains("john"))
        assertTrue(users.contains("jane"))
    }
}