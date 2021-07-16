package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

<<<<<<< HEAD
public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
            FOOTER_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_external_link']";


=======
public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "//*[@resource-id='org.wikipedia:id/view_page_title_text']",
        FOOTER_ELEMENT = "//*[@resource-id='org.wikipedia:id/page_external_link']";
>>>>>>> origin/for_article
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
<<<<<<< HEAD
        return  this.waitForElementPresent(By.xpath(TITLE),"Cannot find article title on page", 10);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getText();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot swipe to footer", 5);
=======
        return this.waitForElementPresent(
                By.xpath(TITLE),
                "Cannot find article title on the page!",
                10);
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getText();
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end article",
                10
        );
>>>>>>> origin/for_article
    }
}
