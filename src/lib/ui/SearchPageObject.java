package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text,'{SUBSTRING}')]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* Templates method */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Templates method */

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search element after clicking searching init element");
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 15);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot click X button!!",
                5
        );
    }

    public void waitForCancelToDisAppear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button still present",
                5
        );
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click cancel search button",
                5
        );
    }
}
