package com.example.demo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import java.io.File

class UserServiceTest {

    private lateinit var userService: UserService

    @TempDir
    lateinit var tempDir: Path

    @BeforeEach
    fun setup() {
        userService = UserService().apply {
            this::class.java.getDeclaredField("filePath").apply {
                isAccessible = true
                set(this@apply, tempDir.resolve("test_users.txt").toString())
            }
        }
    }

    @Test
    fun `test adding users`() {
        userService.addUsername("Alice")
        userService.addUsername("Bob")
        assertEquals(setOf("Alice", "Bob"), userService.getAllUsernames())
    }

    @Test
    fun `test removing existing user`() {
        userService.addUsername("Alice")
        assertFalse(userService.getAllUsernames().contains("Bob"))
    }

    @Test
    fun `persistence test`() {
        userService.addUsername("Alice")
        userService.saveUsersToFile()
        userService = UserService().apply {
            this::class.java.getDeclaredField("filePath").apply {
                isAccessible = true
                set(this@apply, tempDir.resolve("test_users.txt").toString())
            }
        }
        assertEquals(setOf("Alice"), userService.getAllUsernames())
    }
}