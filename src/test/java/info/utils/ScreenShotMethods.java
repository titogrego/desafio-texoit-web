package info.utils;

import info.pages.AbstractPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScreenShotMethods extends AbstractPage {
    /**
     * Method to take screen shot and save in ./screenShots folder
     */
    public void takeScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        String path = "screenShots"+File.separator + dateFormat.format(cal.getTime()) +"-.png";
        System.out.println(path);
        //System.out.println(dateFormat.format(cal.getTime()));
        FileUtils.copyFile(scrFile, new File(path));
    }
}
