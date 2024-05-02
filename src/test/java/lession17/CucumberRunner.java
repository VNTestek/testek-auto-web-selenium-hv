package lession17;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/java/lession17/feature",
        glue = ".",
        plugin = {"pretty", "html:target/cucumber-html-report.html"})
@Test
public class CucumberRunner extends AbstractTestNGCucumberTests {}