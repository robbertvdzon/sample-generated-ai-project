package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.io.File

@Service
class UserService {
    private val users = mutableSetOf<String>()
    private val file = File("users.txt")

    init {
        if (file.exists()) {
            file.readLines().forEach { users.add(it) }
        }
    }

    fun addUsername(username: String) {
        users.add(username)
        file.appendText("$username\n")
    }

    fun deleteUsername(username: String): Boolean {
        if (!users.remove(username)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        } else {
            file.writeText(users.joinToString("\n"))
        }
        return true
    }

    fun getAllUsernames(): Set<String> = users.toSet()
}