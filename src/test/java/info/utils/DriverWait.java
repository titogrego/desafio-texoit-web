package info.utils;

import info.utils.expectedConditions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DriverWait {

    private final Logger logger = LoggerFactory.getLogger(DriverWait.class);

    private final DriverManager driverManager;

    public DriverWait(DriverManager driverManager) {
        this.driverManager = driverManager;
    }



    public void waitForElementToLoad(WebElement element) throws NoSuchFieldException {
        waitForElementVisible(element);
        waitForElementClickable(element);
    }

    public void waitForElementToLoad(By locator) throws NoSuchFieldException {
        waitForElementVisible(locator);
        waitForElementClickable(locator);
    }

    /**
     * wait for element visible by element
     */
    private void waitForElementVisible(WebElement element) {
        try {
            waitLong().until(new VisibilityOfElement(element));
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element visible by locator
     */
    private void waitForElementVisible(By locator) {
        try {
            waitLong().until(new VisibilityOfElementByLocator(locator));
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element Invisible by locator
     */
    private void waitForElementInVisible(By locator) {
        try {
            new InvisibilityOfElementByLocator(locator);
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element Invisible by locator
     */
    private void waitForElementInVisible(WebElement element) {
        try {
            new InvisibilityOfElement(element);
        } catch (Exception ignored) {
        }
    }

    /**
     * wait for element clickable by element
     */
    private void waitForElementClickable(WebElement element) throws NoSuchFieldException {
        try {
            new ClickabilityOfElement(element);
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element " + element);
        }
    }

    /**
     * wait for element clickable by locator
     */
    private void waitForElementClickable(By locator) throws NoSuchFieldException {
        try {
            new ClickabilityOfElementByLocator(locator);
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element by locator " + locator);
        }
    }

    public Wait<WebDriver> waitLong() {
        return new FluentWait<>(driverManager.getDriver())
                .withTimeout(Duration.ofSeconds(Constants.timeoutLong))
                .pollingEvery(Duration.ofMillis(Constants.pollingLong))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    public Wait<WebDriver> waitShort() {
        return new FluentWait<>(driverManager.getDriver())
                .withTimeout(Duration.ofSeconds(Constants.timeoutShort))
                .pollingEvery(Duration.ofMillis(Constants.pollingShort))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }


    private void waitUntilJSReady() {
        final ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driverManager.getDriver())
                .executeScript("return document.readyState")
                .toString()
                .equals("complete");

        boolean jsReady = ((JavascriptExecutor) driverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        if (!jsReady) {
            waitLong().until(jsLoad);
        }
    }

    private void waitForJQueryLoad() {
        final ExpectedCondition<Boolean> jQueryLoad = driver -> (
                (Long) ((JavascriptExecutor) driverManager.getDriver()).executeScript("return jQuery.active") == 0);

        boolean jqueryReady = (Boolean) ((JavascriptExecutor) driverManager.getDriver()).executeScript("return jQuery.active==0");

        if (!jqueryReady) {
            waitLong().until(jQueryLoad);
        }
    }
}
