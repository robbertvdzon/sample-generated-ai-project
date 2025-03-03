package com.vdzon.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@RestController
class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping("/addUser")
    fun addUser(@RequestParam username: String): RedirectView {
        userService.addUsername(username)
        return RedirectView("/")
    }

    @PostMapping("/removeUser")
    fun removeUser(@RequestParam username: String): RedirectView {
        userService.removeUsername(username)
        return RedirectView("/")
    }
}

@Controller
class WebController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("usernames", userService.getAllUsernames())
        return "index"
    }
}