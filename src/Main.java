import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Main {

    private AppiumDriver driver;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTest");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/telepnev/Documents/STQA/Appium/Appium/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot clik the element 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find search text 'Java (programming language)'"
        );
    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot click 'search_container'",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot click 'search_close_btn'",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X button still on the page",
                15
        );
    }

    @Test
    public void testClear() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot click 'search_container'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot click 'search_close_btn'",
                5
        );
//
//        waitForElementNotPresent(
//                By.id("org.wikipedia:id/search_close_btn"),
//                "X button still on the page",
//                15
//        );
    }

    @Test
    public void testCompareArticleTitle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot clik the element 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, 'Java (programming language)')]"),
                "Cannot click 'search_close_btn'",
                5
        );
        WebElement element_title = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Cannot find Article title",
                5
        );
        String article_title = element_title.getText();
        Assert.assertEquals(
                "The title different",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticleTitle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot clik the element 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Appium",
                "",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text, 'Appium')]"),
                "Cannot click 'Appium'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Cannot find Article title",
                5
        );
        swipeUpToFindElement(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_external_link']"),
                "Cannot find end of the article",
                5
        );
    }

    @Test
    public void saveArticleToMyList() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot clik the element 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(@text, 'Java (programming language)')]"),
                "Cannot click 'search_close_btn'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Cannot find Article title",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot click to the More options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot click to the 'Add to reading list'",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button 'GOT IT'",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot clear input field",
                10
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Learning Java",
                "Cannot put text into article folder",
                10
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot click to the 'OK'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot click to the 'Navigate up'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot click to the 'My lists'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Learning Java']"),
                "Cannot click to the 'My reading list'",
                5
        );
        swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find saved article 'Java (programming language)'"
        );
        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot delete article 'Java (programming language)'",
                5
        );

    }


    protected void swipeElementToLeft(By by, String error_message) {
       WebElement element = waitForElementPresent(by, error_message, 10);

       int left_x = element.getLocation().getX();
       int right_x = left_x + element.getSize().getWidth();
       int upper_y = element.getLocation().getY();
       int lower_y = upper_y + element.getSize().getHeight();
       int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(150)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n " + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    protected void swipeUpQuick() {
        swipeUp(200);
    }
    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();            // получаем параметры экрана

        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y).release()
                .perform();
    }
    protected void swipeDown(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();            // получаем параметры экрана

        int x = size.width / 2;
        int start_y = (int) (size.height * 0.2);
        int end_y = (int) (size.height * 0.8);
        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresent(By by, String error_message, long timeOutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.click();
        return element;
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSecond);
        element.clear();
        return element;
    }
}