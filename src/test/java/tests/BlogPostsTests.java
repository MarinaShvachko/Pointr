package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class BlogPostsTests extends BaseTest {

    @Test(description = "The most frequent words from the specified number of articles")
    @Description("just description")
    public void findMoreOftenUsedWords() {
        blogPostsPage.closeAcceptCookieWindow();
        blogPostsPage.writeDataToFile(blogPostsPage.getAllWordsFromArticlesAndCountThem(2));
    }
}