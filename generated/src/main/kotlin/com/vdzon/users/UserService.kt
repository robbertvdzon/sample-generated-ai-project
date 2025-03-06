package com.vdzon.users

class UserService {
    private val usernames = mutableListOf<String>()

    fun addUsername(username: String) {
        usernames.add(username)
    }

    fun getAllUsernames(): List<String> = usernames.toList()

    fun deleteUsername(username: String) {
        usernames.remove(username)
    }
}