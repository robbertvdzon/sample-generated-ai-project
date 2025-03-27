package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.io.File

@Service
class UserService {
    private val users = mutableSetOf<String>()
    private val filePath = "users.txt"

    init {
        loadUsersFromFile()
    }

    fun addUsername(username: String) {
        users.add(username)
        saveUsersToFile()
    }

    fun deleteUsername(username: String): Boolean {
        if (!users.remove(username)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        saveUsersToFile()
        return true
    }

    fun getAllUsernames(): Set<String> = users.toSet()

    private fun saveUsersToFile() {
        File(filePath).writeText(users.joinToString("\n"))
    }

    private fun loadUsersFromFile() {
        val file = File(filePath)
        if (file.exists()) {
            val loadedUsers = file.readLines().toSet()
            users.addAll(loadedUsers)
        }
    }
}