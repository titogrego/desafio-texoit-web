package info.utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriver driver;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    /**
     * By default to web driver will be chrome
     * <p>
     * Override it by passing -Dbrowser=firefox to the command line arguments
     *
     * @return webdriver
     */
    private static WebDriver chooseDriver() {
        String preferredDriver = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless","false"));

        switch (preferredDriver.toLowerCase()) {

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                final FirefoxOptions ffOptions = new FirefoxOptions();

                if (headless) {
                    ffOptions.setHeadless(true);
                }
                return new FirefoxDriver(ffOptions);
            default:
                WebDriverManager.chromedriver().setup();
                final ChromeOptions chromeOptions = new ChromeOptions();

                if (headless) {
                    chromeOptions.addArguments("--headless");
                }

                chromeOptions.addArguments("window-size=1920,1080");
                chromeOptions.addArguments("-incognito");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");

                return new ChromeDriver(chromeOptions);
        }
    }
/*
    public static DesiredCapabilities getCapability(InputStream input) {
        final Properties prop = new Properties();
        DesiredCapabilities capability = new DesiredCapabilities();
        try {
            prop.load(input);
            if (prop.containsKey("app")) {
                String appName = prop.getProperty("app");
                if (!appName.contains("sauce-storage")) {
                    String appPath = System.getProperty("user.dir") + "/src/main/java/appUnderTest/" + appName;
                    prop.setProperty("app", appPath);
                }
            }

            // set capabilities
            Enumeration<Object> enuKeys = prop.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = prop.getProperty(key);
                capability.setCapability(key, value);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return capability;
    }

*/
    public WebDriver getDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        } else {
            return getDefaultDriver();
        }
    }

    public WebDriver getDefaultDriver() {
        DesiredCapabilities capability = null;
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        }
        driver = chooseDriver();

        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        return getDriver();
    }


}
