package com.example.demo

import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@CucumberContextConfiguration
@SpringBootTest(classes = [DemoApplication::class])
@ContextConfiguration
open class CucumberContextConfiguration