package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class BlogPostsPage extends BasePage {

    public BlogPostsPage(WebDriver driver) {
        super(driver);
    }

    //TODO to find an error because I can not initialize @FindBy
//    @FindBy(xpath = "//div//a[contains(text(),'Accept')]")
//    public WebElement buttonAccept;

    //@FindBy(className = "post-body")
    public WebElement textOnThePage;
    private HashMap<String, Integer> wordsInArticleAndItsCount = new HashMap<>();
    private Map<String, Integer> sortedMap;
    private static String PATH_TO_FILE_WITH_RESULTS = "src/Results.txt";
    private File file = new File(PATH_TO_FILE_WITH_RESULTS);
    public List<WebElement> linksToArticle;

    //TODO move xpath out
    @Step("Close cookie window")
    public void closeAcceptCookieWindow() {
        WebElement el = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div//a[contains(text(),'Accept')]")));
        el.click();
    }

    //TODO move xpath out
    public void goToArticle(int numOfArticle) throws InterruptedException {
        linksToArticle = driver.findElements(By.xpath("//div//section[@class='blog-items']//article//a[contains(text(), 'Read more')]"));
        WebElement article = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(linksToArticle.get(numOfArticle)));
        article.click();
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

    //TODO set uo allure report
    @Step("Count words")
    public HashMap<String, Integer> countWordsInArticle(int numOfArticlesToGetWordsForCount) throws InterruptedException {
        for (int article = 0; article < numOfArticlesToGetWordsForCount; article++) {
            goToArticle(article);
            textOnThePage = driver.findElement(By.className("post-body"));
            String articleText = textOnThePage.getText();
            String articleTextLowerCaseWithoutSymbols = articleText.toLowerCase(Locale.ROOT).replaceAll("[^a-zA-ZА-Яа-я0-9\\s]", "");
            String[] words = articleTextLowerCaseWithoutSymbols.split(" ");

            for (String word : words) {
                if (wordsInArticleAndItsCount.containsKey(word)) {
                    wordsInArticleAndItsCount.put(word, wordsInArticleAndItsCount.get(word) + 1);
                } else {
                    wordsInArticleAndItsCount.put(word, 1);
                }
            }
            goToPreviousPage();
        }
        return wordsInArticleAndItsCount;
    }

    public Map<String, Integer> sortValuesInMapDesc(Map<String, Integer> keyValue) {
        sortedMap = keyValue.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        return sortedMap;
    }

    public Map<String, Integer> getAllWordsFromArticlesAndCountThem(int numOfArticles) throws InterruptedException {
        return sortValuesInMapDesc(countWordsInArticle(numOfArticles));
    }

    public void writeDataToFile(Map<String, Integer> sortedMap) throws IOException {
        Set<Map.Entry<String, Integer>> entrySet = sortedMap.entrySet();
        ArrayList<Map.Entry<String, Integer>> listOfEntry = new ArrayList<>(entrySet);

        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        for (int x = 0; x < 5; x++) {
            writer.write((listOfEntry.get(x)) + "\n");
        }
        writer.flush();
        writer.close();
    }

    //TODO when a page with article is opened to get all words create an object on article page
    public ArticlePage goToArticle() {
        //click()
        return new ArticlePage(driver);
    }
}