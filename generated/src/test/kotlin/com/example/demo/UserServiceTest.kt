package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class UserServiceTest {
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserService()
        File("usernames.txt").writeText("") // Clear the file before each test
    }

    @Test
    fun testAddUser() {
        userService.addUsername("Alice")
        assertTrue(userService.getAllUsernames().contains("Alice"))
    }

    @Test
    fun testDeleteUser() {
        userService.addUsername("Bob")
        assertTrue(userService.deleteUsername("Bob"))
        assertFalse(userService.getAllUsernames().contains("Bob"))
    }

    @Test
    fun testPersistence() {
        userService.addUsername("Charlie")
        val userServiceNewInstance = UserService()
        assertTrue(userServiceNewInstance.getAllUsernames().contains("Charlie"))
    }
}