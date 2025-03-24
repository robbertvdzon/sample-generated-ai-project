package com.example.demo

import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com/example/demo",
    tags = "~@ignore"
)
class CucumberTestRunner {
}
