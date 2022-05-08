package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.BlogPostsPage;

public class BaseTest {
    protected WebDriver driver;
    private final String URL = "https://www.pointr.tech/blog";
    protected BlogPostsPage blogPostsPage;
    private static final String PATH_TO_CHROME_DRIVER = "src/main/resources/drivers/chromedriver.exe";
    private static final String PATH_TO_FIREFOX_DRIVER = "src/main/resources/drivers/geckodriver.exe";
    private static final String USE_CHROME_BROWSER = "Chrome";
    private static final String USE_FIREFOX_BROWSER = "Firefox";

    @Parameters({"browser", "driverexe"})
    @BeforeClass
    public void setUp(@Optional(USE_CHROME_BROWSER) String browser, @Optional(PATH_TO_CHROME_DRIVER) String driverexe) {
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", driverexe);
            driver = new ChromeDriver();
        } else if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", PATH_TO_FIREFOX_DRIVER);
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        //driver.get(URL);
        //blogPostsPage = new BlogPostsPage(driver);
    }

    public void goToBlogs() {
        driver.get(URL);
        blogPostsPage = new BlogPostsPage(driver);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        driver.quit();
    }
}
