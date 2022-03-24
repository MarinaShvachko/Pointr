package com.pointr.pages;

import com.pointr.elements.BlogPageElements;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BlogPage {
    private final String PAGE_URL = "https://www.pointr.tech/blog";
    private static String PATH_TO_FILE_WITH_RESULTS = "C:\\Users\\Xiaomi\\Desktop\\Test.txt";
    WebDriver driver;
    private BlogPageElements blogPageElement;
    private HashMap<String, Integer> wordsInArticleAndItsCount = new HashMap<>();
    private Map<String, Integer> sortedMap;
    private File file = new File(PATH_TO_FILE_WITH_RESULTS);

    public BlogPage(WebDriver driver) {
        this.driver = driver;
        blogPageElement = new BlogPageElements(driver);
    }

    public String getPageURL() {
        return PAGE_URL;
    }

    public void closeAcceptCookieWindow() {
        blogPageElement.buttonAccept.click();
    }

    public void goToArticle(int numOfArticle) {
        blogPageElement.linksToArticle.get(numOfArticle).click();
    }

    public void goToPreviousPage() {
        driver.navigate().back();
    }

    public HashMap<String, Integer> countWordsInArticle(int numOfArticlesToGetWordsForCount) {
        for (int article = 0; article < numOfArticlesToGetWordsForCount; article++) {
            goToArticle(article);
            String articleText = blogPageElement.textOnThePage.getText();
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
}
