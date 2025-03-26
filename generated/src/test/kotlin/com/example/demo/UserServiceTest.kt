package com.example.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.BeforeEach
import java.io.File

@SpringBootTest
class UserServiceTest {

    private lateinit var userService: UserService

    @BeforeEach
    fun setup() {
        val file = File("users.txt")
        if (file.exists()) file.delete()
        userService = UserService()
    }

    @Test
    fun testAddUser() {
        userService.addUsername("Alice")
        assertTrue(userService.getAllUsernames().contains("Alice"))
        assertTrue(File("users.txt").readLines().contains("Alice"))
    }

    @Test
    fun testDeleteUser() {
        userService.addUsername("Bob")
        userService.deleteUsername("Bob")
        assertFalse(userService.getAllUsernames().contains("Bob"))
        assertFalse(File("users.txt").readLines().contains("Bob"))
    }

    @Test
    fun testLoadUsersFromFile() {
        File("users.txt").writeText("Carol")
        userService = UserService()
        assertTrue(userService.getAllUsernames().contains("Carol"))
    }
}