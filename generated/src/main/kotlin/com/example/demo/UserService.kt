package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.charset.StandardCharsets

@Service
class UserService {
    private val users = mutableSetOf<String>()
    private val filePath = "usernames.txt"

    init {
        loadUsernamesFromFile()
    }

    fun addUsername(username: String) {
        users.add(username)
        saveUsernamesToFile()
    }

    fun deleteUsername(username: String): Boolean {
        if (!users.remove(username)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        saveUsernamesToFile()
        return true
    }

    fun getAllUsernames(): Set<String> = users.toSet()

    private fun saveUsernamesToFile() {
        Files.write(Paths.get(filePath), users, StandardCharsets.UTF_8)
    }

    private fun loadUsernamesFromFile() {
        val path = Paths.get(filePath)
        if (Files.exists(path)) {
            users.addAll(Files.readAllLines(path, StandardCharsets.UTF_8))
        }
    }
}