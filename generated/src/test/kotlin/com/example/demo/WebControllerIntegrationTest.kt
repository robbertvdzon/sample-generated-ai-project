package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebControllerIntegrationTest(@Autowired val restTemplate: TestRestTemplate,
                                   @LocalServerPort val port: Int) {

    @Test
    fun `should render header correctly`() {
        val result: ResponseEntity<String> = restTemplate.getForEntity("http://localhost:$port/", String::class.java)
        assertTrue(result.statusCode == HttpStatus.OK)
        assertTrue(result.body!!.contains("Contacten Database"))
        assertTrue(result.body!!.contains("AI generated application"))
    }

    @Test
    fun `should add user and reflect on homepage`() {
        val form = mapOf("username" to "NewUser")
        val request = HttpEntity(form)
        restTemplate.postForEntity("http://localhost:$port/addUser", request, String::class.java)

        val result = restTemplate.getForEntity("http://localhost:$port/", String::class.java)
        assertTrue(result.statusCode == HttpStatus.OK)
        assertTrue(result.body!!.contains("NewUser"))
    }
}