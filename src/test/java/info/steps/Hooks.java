package info.steps;


import info.pages.AbstractPage;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hooks extends AbstractPage  {
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    @AfterStep
    public void addPrintAfterStep(Scenario scenario){
        String evidence = System.getProperty("evidence", "false");

        switch (evidence) {
            default:
                break;
            case  "true":
            if (scenario.getStatus() != null && !scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        endOfTest(scenario);
    }

    public void endOfTest(Scenario scenario) {
        if (scenario.getStatus() != null && scenario.isFailed()) {
          byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
    }

        log.info("");
        log.info("==========================================================================");
        log.info("================================Test " + scenario.getStatus().toString() + "===============================");
        log.info("==========================================================================");
        log.info("");
    }
}
