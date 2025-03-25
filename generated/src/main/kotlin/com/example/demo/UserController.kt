package com.example.demo

import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

@RestController
class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping("/addUser")
    fun addUser(@RequestParam username: String, model: Model): ResponseEntity<String> {
        userService.addUsername(username)
        return ResponseEntity.ok().build()
    }
}

//@Controller
//class IndexController {
//    @Autowired
//    lateinit var userService: UserService
//
//    @GetMapping("")
//    fun index(model: Model): String {
//        model.addAttribute("usernames", userService.getAllUsernames())
//        return "index"
//    }
//}

@RestController
class DeleteUserController {
    @Autowired
    lateinit var userService: UserService

    @DeleteMapping("/deleteUser/{username}")
    fun deleteUser(@PathVariable username: String): ResponseEntity<String> {
        userService.deleteUsername(username)
        return ResponseEntity.ok().build()
    }
}