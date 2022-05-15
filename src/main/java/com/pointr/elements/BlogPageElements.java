package com.pointr.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BlogPageElements {
    WebDriver driver;

    @FindBy(xpath = "//section/article//a[contains(text(),'Read more')]")
    public List<WebElement> linksToArticle;

    @FindBy(xpath = "//div//a[contains(text(),'Accept')]")
    public WebElement buttonAccept;

    public BlogPageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "post-body")
    public WebElement textOnThePage;
}
