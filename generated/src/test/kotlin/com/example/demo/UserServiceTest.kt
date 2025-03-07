package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserServiceTest {

    private val userService = UserService()

    @Test
    fun testAddAndGetUsernames() {
        userService.addUsername("John")
        userService.addUsername("Jane")
        val usernames = userService.getAllUsernames()

        assertEquals(2, usernames.size)
        assertTrue(usernames.contains("John"))
        assertTrue(usernames.contains("Jane"))
    }
}