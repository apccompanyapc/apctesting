package org.apctesting.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.apctesting.steps"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@test"
)
public class TestRunner {
}

