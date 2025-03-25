package com.example.demo

import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus

@Service
class UserService {
    private val users = mutableSetOf<String>()

    fun addUsername(username: String) {
        users.add(username)
    }

    fun deleteUsername(username: String): Boolean {
        if (!users.remove(username)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        }
        return true
    }

    fun getAllUsernames(): Set<String> = users.toSet()
}