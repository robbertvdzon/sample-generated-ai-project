package com.example.demo

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

@RestController
class DeleteUserController {
    @Autowired
    lateinit var userService: UserService

    @DeleteMapping("/deleteUser/{username}")
    fun deleteUser(@PathVariable username: String): ResponseEntity<String> {
        if (userService.deleteUsername(username)) {
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.status(404).body("User not found")
        }
    }
}