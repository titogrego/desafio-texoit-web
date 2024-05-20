package info.utils;

import info.pages.AbstractPage;

public class JavascriptHandlingMethods extends AbstractPage {
    /**
     * Method to handle alert
     *
     * @param decision : String : Accept or dismiss alert
     */
    public void handleAlert(String decision) {
        if (decision.equals("accept"))
            getDriver().switchTo().alert().accept();
        else
            getDriver().switchTo().alert().dismiss();
    }
}
