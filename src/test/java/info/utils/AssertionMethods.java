package info.utils;

import info.pages.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



public class AssertionMethods extends AbstractPage implements BaseTest {
    // This file contains assertion methods which are called from
    // predefinedStepDefinitions

    private final SelectElementByType selectElementByType = new SelectElementByType();
    // SelectElementByType eleType= new SelectElementByType();
    private WebElement element = null;

    /**
     * Method to get page title
     *
     * @return String
     */
    public String getPageTitle() {
        return getDriver().getTitle();
    }

    /**
     * Method to verify page title
     *
     * @param title    : String : expected title
     * @param testCase : Boolean : test case [true or false]
     */
    public void checkTitle(String title, boolean testCase)  {
        String pageTitle = getPageTitle();

            assertThat(pageTitle,is(title));

            assertThat(pageTitle,not(is(title)));

    }





    /**
     * Method to verify partial page title
     *
     * @param partialTitle : String : partial title string
     * @param testCase     : Boolean : test case [true or false]
     */
    public void checkPartialTitle(String partialTitle, boolean testCase) {
        String pageTitle = getPageTitle();
        if (testCase) {
            assertThat(pageTitle,containsString(pageTitle));
        } else {
            assertThat(pageTitle,not(containsString(pageTitle)));
        }
    }

    /**
     * Method to get element text
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @return String
     */
    public String getElementText(String accessType, String accessName) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        return element.getText();

    }

    /**
     * Method to size of list elements
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @return String
     */
    public int getSizeOfElements(String accessType, String accessName) {
        List<WebElement> element = getDriverWait().waitShort().until(ExpectedConditions.numberOfElementsToBeMoreThan(selectElementByType.getelementbytype(accessType, accessName),1));
        return element.size();

    }

    /**
     * Method to get a list of elements
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @return String
     */
    public List<WebElement> getListOfElements(String accessType, String accessName) {
        List<WebElement> element = getDriverWait().waitShort().until(ExpectedConditions.numberOfElementsToBeMoreThan(selectElementByType.getelementbytype(accessType, accessName),1));
        return element;
    }




    /**
     * Method to check quantity elements on the screen
     *
     * @param accessType  : String : Locator type (id, name, class, xpath, css)
     * @param quantity : int : Expected  quantity of element
     * @param accessName  : String : Locator value
     * @param testCase    : Boolean : test case [greater or less]
     */
    public void checkQuantityOfElements(String accessType, int quantity, String accessName, String testCase){

        int size = getSizeOfElements(accessType, accessName);

        if (testCase.equals("greater")) {
           assertThat(size,greaterThan(quantity));
        }
        if (testCase.equals("less"))  {
            assertThat(size,lessThan(quantity));
        }
    }

    /**
     * Method to check element text
     *
     * @param accessType  : String : Locator type (id, name, class, xpath, css)
     * @param actualValue : String : Expected element text
     * @param accessName  : String : Locator value
     * @param testCase    : Boolean : test case [true or false]
     */
    public void checkElementText(String accessType, String actualValue, String accessName, boolean testCase)
             {
        String elementText = getElementText(accessType, accessName);
        assertThat(elementText,is(actualValue));
    }

    /**
     * Method to check element text
     *
     * @param text : String : Expected  text is visible in the screen
     * @param testCase    : Boolean : test case [true or false]
     */
    public void checkTextIsVisible(String text, boolean testCase)
    {
        String locator = "//*[contains(text(),'"+text+"')]";
        System.out.println(locator);
        element = getDriverWait().waitShort().until(ExpectedConditions.visibilityOfElementLocated(selectElementByType.getelementbytype("xpath", locator)));
        boolean isvisible = element.isDisplayed();
        assertThat(isvisible,is(testCase));
    }

    /**
     * Method to check partial element text
     *
     * @param accessType  : String : Locator type (id, name, class, xpath, css)
     * @param actualValue : String : Expected element text
     * @param accessName  : String : Locator value
     * @param testCase    : Boolean : test case [true or false]
     */
    public void checkElementPartialText(String accessType, String actualValue, String accessName, boolean testCase){
        String elementText = getElementText(accessType, accessName);

        if (testCase) {
            assertThat(elementText,containsString(actualValue));
        } else {
            assertThat(elementText,not(containsString(actualValue)));
        }
    }

    /**
     * Method to return element status - enabled?
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @return Boolean
     */
    public boolean isElementEnabled(String accessType, String accessName) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        return element.isEnabled();
    }

    /**
     * Element enabled checking
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @param testCase   : Boolean : test case [true or false]
     */
    public void checkElementEnable(String accessType, String accessName, boolean testCase) {
        boolean result = isElementEnabled(accessType, accessName);
        assertThat(result,is(testCase));
    }

    /**
     * method to get attribute value
     *
     * @param accessType    : String : Locator type (id, name, class, xpath, css)
     * @param accessName    : String : Locator value
     * @param attributeName : String : attribute name
     * @return String
     */
    public String getElementAttribute(String accessType, String accessName, String attributeName) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        return element.getAttribute(attributeName);
    }

    /**
     * method to check attribute value
     *
     * @param accessType     : String : Locator type (id, name, class, xpath, css)
     * @param attributeName  : String : attribute name
     * @param attributeValue : String : attribute value
     * @param accessName     : String : Locator value
     * @param testCase       : Boolean : test case [true or false]
     */
    public void checkElementAttribute(String accessType, String attributeName, String attributeValue, String accessName,
                                      boolean testCase) {
        String attrVal = getElementAttribute(accessType, accessName, attributeName);
        assertThat(attrVal,is(attributeName));
    }

    /**
     * method to get element status - displayed?
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @return Boolean
     */
    public boolean isElementDisplayed(String accessType, String accessName) {
        element = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        return element.isDisplayed();
    }

    /**
     * method to check element presence
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @param testCase   : Boolean : test case [true or false]
     */
    public void checkElementPresence(String accessType, String accessName, boolean testCase) {

       boolean isDisplayed = isElementDisplayed(accessType, accessName);
        assertThat(isDisplayed, is(testCase));
    }

    /**
     * method to assert checkbox check/uncheck
     *
     * @param accessType      : String : Locator type (id, name, class, xpath, css)
     * @param accessName      : String : Locator value
     * @param shouldBeChecked : Boolean : test case [true or false]
     */
    public void isCheckboxChecked(String accessType, String accessName, boolean shouldBeChecked) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions
                 .presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        boolean isSelected = checkbox.isSelected();
        assertThat(isSelected, is(shouldBeChecked));

    }

    /**
     * method to assert radio button selected/unselected
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void isRadioButtonSelected(String accessType, String accessName, boolean shouldBeSelected)
            {
        WebElement radioButton = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
                boolean isSelected = radioButton.isSelected();
                assertThat(isSelected, is(shouldBeSelected));
    }


    /**
     * method to get javascript pop-up alert text
     *
     * @return String
     */
    public String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    /**
     * method to check javascript pop-up alert text
     *
     * @param text : String : Text to verify in Alert
     */
    public void checkAlertText(String text) {
       assertThat(text,is(getAlertText()));
    }


}
