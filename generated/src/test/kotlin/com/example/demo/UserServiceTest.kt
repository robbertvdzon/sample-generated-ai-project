package com.example.demo

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserServiceTest {

    private lateinit var userService: UserService

    @TempDir
    lateinit var tempDir: Path

    @BeforeEach
    fun setup() {
        val filePath = tempDir.resolve("users.txt")
        Files.write(filePath, listOf("Alice", "Bob"))
        userService = UserService(tempDir.toFile())
    }

    @Test
    fun `should add a user and persist to file`() {
        userService.addUsername("Charlie")
        assertTrue(userService.getAllUsernames().contains("Charlie"))
        assertTrue(tempDir.resolve("users.txt").toFile().readLines().contains("Charlie"))
    }

    @Test
    fun `should remove a user and update file`() {
        userService.deleteUsername("Alice")
        assertFalse(userService.getAllUsernames().contains("Alice"))
    }

    @Test
    fun `should throw exception when deleting non-existent user`() {
        assertFailsWith<ResponseStatusException> {
            userService.deleteUsername("NonExistent")
        }
    }
}