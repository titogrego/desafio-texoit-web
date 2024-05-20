package info.pages;

import info.annotations.PageObject;
import info.utils.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@PageObject
public class HomePage extends AbstractPage {

    @FindBy(how = How.LINK_TEXT, using = "Guide")
    public WebElement menuGuide;





    public void clicarMenuGuide(){
        BaseTest.clickObj.clickWebElement(menuGuide);
    }

    public void clicarNoLink(String link){
        BaseTest.navigationObj.scrollToElement("linkText", link);
        BaseTest.clickObj.click("linkText", link);
    }
    public String retornaDadosFotos(){
        return BaseTest.assertionObj.getElementText("css", "pre");
    }

}

