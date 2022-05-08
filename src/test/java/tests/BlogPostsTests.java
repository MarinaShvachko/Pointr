package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.io.IOException;

public class BlogPostsTests extends BaseTest {

    @Test(description = "The most frequent words from the specified number of articles")
    @Description("just description")
    public void findMoreOftenUsedWords() throws InterruptedException, IOException {
        goToBlogs();
        blogPostsPage.closeAcceptCookieWindow();
        blogPostsPage.writeDataToFile(blogPostsPage.getAllWordsFromArticlesAndCountThem(3));
    }
}
