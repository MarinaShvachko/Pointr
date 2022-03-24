package com.pointr.tests;

import com.pointr.lib.AppLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class AbstractBaseTest {
    WebDriver driver;
    private AppLib app;
    private static final String PATH_TO_CHROME_DRIVER = "C:/Users/Xiaomi/Documents/projects/webdrivers/chromedriver.exe";
    private static final String PATH_TO_FIREFOX_DRIVER = "C:/Users/Xiaomi/Documents/projects/webdrivers/geckodriver.exe";
    private static final String USE_CHROME_BROWSER = "Chrome";
    private static final String USE_FIREFOX_BROWSER = "Firefox";

    @Parameters({"browser", "driverexe"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional(USE_CHROME_BROWSER) String browser, @Optional(PATH_TO_CHROME_DRIVER) String driverexe) throws Exception {
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", driverexe);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", PATH_TO_FIREFOX_DRIVER);
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        app = new AppLib(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    public AppLib App() {
        return app;
    }
}
