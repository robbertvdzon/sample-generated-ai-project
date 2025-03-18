package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun addUser(@RequestParam username: String): String {
        userService.addUsername(username)
        return "User added"
    }

    @DeleteMapping("/{username}")
    fun deleteUser(@PathVariable username: String): ResponseEntity<String> {
        return if (userService.deleteUsername(username)) ResponseEntity.ok("User deleted")
        else ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found")
    }
}

@Controller
class WebController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("")
    fun index(model: Model): String {
        model.addAttribute("usernames", userService.getAllUsernames())
        return "index"
    }
}