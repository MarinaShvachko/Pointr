package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    static final Logger log = Logger.getLogger(BasePage.class);
}
