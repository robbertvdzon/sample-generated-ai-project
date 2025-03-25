package com.example.demo

import io.cucumber.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features/RemoveUser.feature"],
    glue = ["com.example.demo"],
    plugin = ["pretty"]
)
class CucumberRunner