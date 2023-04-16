package driver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();


    //if threadlocal doesn't contain driver object, store inside thread variable and create driver
    public static WebDriver getDriver(){
        if (webDriver.get() == null){
            webDriver.set(createDriver());
        }
        return webDriver.get();
    }


    private static WebDriver createDriver() {
        WebDriver driver = null;

        switch (getBrowserType()) {
            case "chrome" -> {
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/main/java/driver/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable notifications");
                DesiredCapabilities cp = new DesiredCapabilities();
                cp.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                chromeOptions.merge(cp);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox" -> {
                System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/main/java/driver/drivers/geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static String getBrowserType() {
        String browserType = null;
        String browserTypeRemoteValue = System.getProperty("browserType");

        try{
            if(browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()){
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
            properties.load(file);
            browserType = properties.getProperty("browser").toLowerCase().trim(); //to ensure chrome is lowercase and there's no blank
        } else {
            browserType = browserTypeRemoteValue;
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        return browserType;
    }

    public static void cleanupDriver() {
        webDriver.get().quit();
        webDriver.remove();
    }
}
