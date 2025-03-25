package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.nio.charset.StandardCharsets

@RestController
class DownloadCsvController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/downloadCsv")
    fun downloadCsv(): ResponseEntity<ByteArray> {
        val csvContent = userService.getAllUsernames().joinToString(separator = "\n") { it }
        val csvData = csvContent.toByteArray(StandardCharsets.UTF_8)
        val headers = HttpHeaders()
        headers.contentType = MediaType.TEXT_PLAIN
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contacts.csv")
        return ResponseEntity.ok()
            .headers(headers)
            .body(csvData)
    }
}