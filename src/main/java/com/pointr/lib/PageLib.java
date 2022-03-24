package com.pointr.lib;

import com.pointr.pages.BlogPage;
import org.openqa.selenium.WebDriver;

public class PageLib {
    private WebDriver driver;
    private BlogPage blogPage;

    public PageLib(WebDriver driver) {
        this.driver = driver;
        blogPage = new BlogPage(this.driver);
    }

    public BlogPage BlogPage() {
        return blogPage;
    }
}
