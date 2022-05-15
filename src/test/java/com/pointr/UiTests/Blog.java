package com.pointr.UiTests;

import org.testng.annotations.Test;

import java.io.IOException;

public class Blog extends AbstractBaseTest {
    @Test
    public void fiveMostUsedWords() throws IOException {
        App().Flow().navigateToUrl(App().Pages().BlogPage().getPageURL());
        App().Pages().BlogPage().closeAcceptCookieWindow();
        App().Pages().BlogPage().writeDataToFile(App().Pages().BlogPage().getAllWordsFromArticlesAndCountThem(3));
    }
}
