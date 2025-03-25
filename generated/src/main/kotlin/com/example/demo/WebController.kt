package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

@Controller
class WebController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("")
    fun index(model: Model): String {
        model.addAttribute("usernames", userService.getAllUsernames())
        return "index"
    }

    @GetMapping("/downloadContacts")
    fun downloadContacts(): ResponseEntity<String> {
        val contacts = userService.getAllUsernames().joinToString(separator = "\n") { it }
        val headers = HttpHeaders()
        headers.setContentType(MediaType.TEXT_PLAIN)
        headers.setContentDispositionFormData("attachment", "contacts.csv")
        return ResponseEntity.ok()
            .headers(headers)
            .body(contacts)
    }
}
