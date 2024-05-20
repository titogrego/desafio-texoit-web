package info.utils;

import info.pages.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InputMethods extends AbstractPage implements BaseTest {
    private final SelectElementByType selectElementByType = new SelectElementByType();
    private WebElement dropdown = null;
    private Select selectList = null;

    /**
     * Method to enter text into text field
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param text       : String : Text value to enter in field
     * @param accessName : String : Locator value
     */
    public void enterText(String accessType, String text, String accessName) {
         WebElement element = getDriverWait().waitLong().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        getDriverWait().waitLong().until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);

    }

    /**
     * Method to enter text into text field by list index
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param text       : String : Text value to enter in field
     * @param accessName : String : Locator value
     * @param index : int : index to list
     */
    public void enterTextByList(String accessType, String text, String accessName,int index) {
        List<WebElement> element = getDriverWait()
                .waitLong()
                .until(ExpectedConditions
                        .numberOfElementsToBeMoreThan(selectElementByType
                                .getelementbytype(accessType, accessName),0));

        element.get(index).clear();
        element.get(index).sendKeys(text);

    }
    /**
     * Method to send key command by list
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param command       : String : Text value to enter in field
     * @param accessName : String : Locator value
     * @param index : int : index to list
     */
    public void enterKeyByList(String accessType, String command, String accessName,int index) {
        List<WebElement> element = getDriverWait()
                .waitLong()
                .until(ExpectedConditions
                        .numberOfElementsToBeMoreThan(selectElementByType
                                .getelementbytype(accessType, accessName),0));


        element.get(index).sendKeys(getKey(command));

    }




    /**
     * Method to enter text into text field by list index
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param command       : String : KEy comand (tab. enter, esc, backspace)
     * @param accessName : String : Locator value

     */
    public void sendKeyboardKey(String accessType,  String accessName,String command) {
        WebElement element = getDriverWait().waitLong().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        getDriverWait().waitLong().until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(getKey(command));
    }



    /**
     * Method to enter text to webelement
     *
     * @param element : Webelement : element
     * @param text       : String : Text value to enter in field
    */
    public void enterTextToElement(WebElement element, String text){
        getDriverWait().waitLong().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Method to clear text of text field
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void clearText(String accessType, String accessName) {
        getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        getDriver().findElement(selectElementByType.getelementbytype(accessType, accessName)).clear();
    }

    /**
     * Method to select element from Dropdown by type
     *
     * @param select_list : Select : Select variable
     * @param bytype      : String : Name of by type
     * @param option      : String : Option to select
     */
    public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
        if (bytype.equals("selectByIndex")) {
            int index = Integer.parseInt(option);
            select_list.selectByIndex(index - 1);
        } else if (bytype.equals("value"))
            select_list.selectByValue(option);
        else if (bytype.equals("text"))
            select_list.selectByVisibleText(option);
    }

    /**
     * Method to select option from dropdown list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param option     : String : Option to select
     * @param accessName : String : Locator value
     */
    public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);

        if (optionBy.equals("selectByIndex"))
            selectList.selectByIndex(Integer.parseInt(option) - 1);
        else if (optionBy.equals("value"))
            selectList.selectByValue(option);
        else if (optionBy.equals("text"))
            selectList.selectByVisibleText(option);
    }


    /**
     * Method to unselect all option from dropdwon list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);
        selectList.deselectAll();
    }

    /**
     * Method to unselect option from dropdwon list
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        dropdown = getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);

        if (optionBy.equals("selectByIndex"))
            selectList.deselectByIndex(Integer.parseInt(option) - 1);
        else if (optionBy.equals("value"))
            selectList.deselectByValue(option);
        else if (optionBy.equals("text"))
            selectList.deselectByVisibleText(option);
    }

    /**
     * Method to check check-box
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void checkCheckbox(String accessType, String accessName) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        if (!checkbox.isSelected())
            checkbox.click();
    }

    /**
     * Method to uncheck check-box
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void uncheckCheckbox(String accessType, String accessName) {
        WebElement checkbox = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        if (checkbox.isSelected())
            checkbox.click();
    }

    /**
     * Method to toggle check-box status
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void toggleCheckbox(String accessType, String accessName) {
        getDriverWait().waitShort().until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName))).click();
    }

    /**
     * Method to select radio button
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void selectRadioButton(String accessType, String accessName) {
        WebElement radioButton = getDriverWait().waitShort()
                .until(ExpectedConditions.presenceOfElementLocated(selectElementByType.getelementbytype(accessType, accessName)));
        if (!radioButton.isSelected())
            radioButton.click();
    }

    /**
     * Method to select option from radio button group
     *
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param by         : String : Name of by type
     * @param option     : String : Option to select
     * @param accessName : String : Locator value
     */
    public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName) {
        List<WebElement> radioButtonGroup = getDriver().findElements(selectElementByType.getelementbytype(accessType, accessName));
        for (WebElement rb : radioButtonGroup) {
            if (by.equals("value")) {
                if (rb.getAttribute("value").equals(option) && !rb.isSelected())
                    rb.click();
            } else if (by.equals("text")) {
                if (rb.getText().equals(option) && !rb.isSelected())
                    rb.click();
            }
        }
    }

    /**
     * Method to return key command
     *
     * @param command : String : command key type (enter, esc, tab, backspae, del)
     * @return Key :

     */

    public Keys getKey(String command) {

        switch (command) {
            case "enter":
               return Keys.ENTER;
            case "tab":
               return Keys.TAB;
            case "esc":
               return Keys.ESCAPE;
            case "backspace":
                return Keys.BACK_SPACE;
            case "del":
                return Keys.DELETE;
            default:
                return Keys.ALT;

        }
    }
}
