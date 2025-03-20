package com.example.demo

import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = "com.example.demo")
class UserControllerTest {
}
