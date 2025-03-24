package com.example.demo

import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebClient

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    @Bean
    fun webClient(@Value("${local.server.port}") port: Int): WebClient {
        return WebClient.create("http://localhost:$port")
    }
}