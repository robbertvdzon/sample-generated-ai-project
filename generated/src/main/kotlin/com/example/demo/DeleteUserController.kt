package com.example.demo

import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.beans.factory.annotation.Autowired

@RestController
class DeleteUserController {
    @Autowired
    lateinit var userService: UserService

    @DeleteMapping("/deleteUser/{username}")
    fun deleteUser(@PathVariable username: String): String {
        userService.deleteUsername(username)
        return "redirect:/"
    }
}