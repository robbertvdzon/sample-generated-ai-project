package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import java.io.File

@Service
class UserService {
    private val users = mutableSetOf<String>()
    private val filename = "users.txt"

    init {
        loadUsers()
    }

    fun addUsername(username: String) {
        users.add(username)
        saveUsers()
    }

    fun deleteUsername(username: String): Boolean {
        if (!users.remove(username)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        saveUsers()
        return true
    }

    fun getAllUsernames(): Set<String> = users.toSet()

    private fun saveUsers() {
        File(filename).printWriter().use { out ->
            users.forEach { out.println(it) }
        }
    }

    private fun loadUsers() {
        val file = File(filename)
        if (file.exists()) {
            file.forEachLine { users.add(it.trim()) }
        }
    }
}