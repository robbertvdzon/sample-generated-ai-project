package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.io.File

@Service
class UserService {
    private val users = loadUsernamesFromFile()

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

    private fun loadUsernamesFromFile(): MutableSet<String> {
        val file = File("users.txt")
        if (!file.exists()) file.createNewFile()
        return file.readLines().toMutableSet()
    }

    private fun saveUsernamesToFile() {
        val file = File("users.txt")
        file.writeText(users.joinToString(System.lineSeparator()))
    }
}