package info.utils;

import info.pages.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ClickElementsMethods extends AbstractPage implements BaseTest {
        private final SelectElementByType selectElementByType = new SelectElementByType();
        private WebElement element = null;

        /**
         * Method to click on an element
         *
         * @param accessType : String : Locator type (id, name, class, xpath, css)
         * @param accessName : String : Locator value
         */
        public void click(String accessType, String accessName) {
            element = getDriverWait().waitShort().until(ExpectedConditions.visibilityOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
            element.click();
        }

    /**
     * Method to click in text
     *
     * @param text : text to click
     */

    public void clickByText(String text) {
        String accessName = "//*[contains(text(),'"+text+"')]";
        String accessType = "xpath";
        element = getDriverWait().waitShort().until(ExpectedConditions.visibilityOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        element.click();
    }


        /**
         * Method to click on an Web element
         *
         * @param element : Webelement : Webelement to click
         */

        public void clickWebElement(WebElement element) {
            element = getDriverWait().waitShort().until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }

        /**
         * Method to forcefully click on an element
         *
         * @param accessType : String : Locator type (id, name, class, xpath, css)
         * @param accessName : String : Locator value
         */
        public void clickForcefully(String accessType, String accessName) {
            element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].click();", element);
        }

    /**
     * Method to click by list elements index
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void clickByList(String accessType, String accessName,int index) {
         List<WebElement> element = getDriverWait().waitLong().until(ExpectedConditions.numberOfElementsToBeMoreThan(selectElementByType.getelementbytype(accessType, accessName),0));
         getDriverWait().waitLong().until(ExpectedConditions.elementToBeClickable(element.get(index)));
         element.get(index).click();
    }

        /**
         * Method to Double click on an element
         *
         * @param accessType : String : Locator type (id, name, class, xpath, css)
         */
        public void doubleClick(String accessType, String accessValue) {
            element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessValue)));

            Actions action = new Actions(getDriver());
            action.moveToElement(element).doubleClick().perform();
        }
}


