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
    public WebElement buttonAcceptCookie = driver.findElement(By.id("hs-eu-confirmation-button"));

    private WebElement textOnThePage;
    private HashMap<String, Integer> wordsInArticleAndItsCount = new HashMap<>();
    private Map<String, Integer> sortedMap;
    private List<WebElement> linksToArticle;
    private File file = new File(PATH_TO_FILE_WITH_RESULTS);
    private static String PATH_TO_FILE_WITH_RESULTS = "src/Results.txt";
    private static final String PATH_TO_ARTICLES = "//article[@class='blog-posts']//a[contains(text(), 'Read more')]";

    @Step("Close cookie window")
    public void closeAcceptCookieWindow() {
        buttonAcceptCookie.click();
    }

    public void goToArticle(int numOfArticle){
        linksToArticle = driver.findElements(By.xpath(PATH_TO_ARTICLES));
        WebElement article = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(linksToArticle.get(numOfArticle)));
        article.click();
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

    @Step("Count words in articles")
    public HashMap<String, Integer> countWordsInArticle(int numOfArticlesToGetWordsForCount){
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

    public Map<String, Integer> getAllWordsFromArticlesAndCountThem(int numOfArticles) {
        return sortValuesInMapDesc(countWordsInArticle(numOfArticles));
    }

    public void writeDataToFile(Map<String, Integer> sortedMap) {
        Set<Map.Entry<String, Integer>> entrySet = sortedMap.entrySet();
        ArrayList<Map.Entry<String, Integer>> listOfEntry = new ArrayList<>(entrySet);

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (int x = 0; x < 5; x++) {
                writer.write((listOfEntry.get(x)) + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}