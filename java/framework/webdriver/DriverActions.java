package framework.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class DriverActions extends Actions {

    private WebDriver driver;
    private WebDriverWait wait;

    public DriverActions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 10);
    }

    public void waitUntilJqueryIsDone() {
        waitUntilJqueryIsDone((long) 60);
    }

    /**
     * Waits until there are no ajax calls happening in the browser before moving on and interacting with page elements
     */
    private void waitUntilJqueryIsDone(Long timeoutInSeconds) {
        until(
                (d) -> {
                    return (Boolean)
                            ((JavascriptExecutor) driver)
                                    .executeScript("return window.jQuery == undefined || jQuery.active == 0");
                },
                timeoutInSeconds);
    }

    public void waitUntilPageLoadComplete() {
        waitUntilPageLoadComplete((long) 60);
    }

    /**
     * Waits until the entire DOM and page is fully loaded before moving on and interacting with page elements
     */
    private void waitUntilPageLoadComplete(Long timeoutInSeconds) {
        until(
                (d) -> {
                    return ((JavascriptExecutor) driver)
                            .executeScript("return document.readyState")
                            .equals("complete");
                },
                timeoutInSeconds);
    }

    public void until(Function<WebDriver, Boolean> waitCondition) {
        until(waitCondition, (long) 60);
    }

    /**
     * Waits for a certain condition before moving on and interacting with page elements
     */
    private void until(Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        //webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));//withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        webDriverWait.until(waitCondition);
    }

    /**
     * Scrolls element where we can click it
     * element -> the element to be scrolled
     */
    public void scrollElementIntoView(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        jse.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        waitUntilJqueryIsDone();
    }

    /**
     * This method types a string inside a field element
     */
    public void sendKeys(WebElement element, String keys)
            throws NoSuchElementException, InterruptedException {

        element.clear();
        for (int index = 0; index < keys.length(); index++) {
            element.sendKeys(keys.charAt(index) + "");
            Thread.sleep(300);
        }
        // If a sequence of several keys are sent without a delay, different errors
        // may occur. This seems to be not really a bug, because the human user cannot
        // send keys without a delay.
    }

    public void clearTextField(WebElement element) throws InterruptedException {
        sendKeys(element, Keys.CONTROL + "a");
        sendKeys(element, "" + Keys.DELETE);
    }

    public void select(WebElement element) throws NoSuchElementException {
        waitUntilJqueryIsDone();
        wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
    }

    public void waitUntilElementIsFullyLoaded(WebElement element) throws InterruptedException {
        waitUntilJqueryIsDone();
        waitUntilElementIsDisplayed(element, 5000);
    }

    public void waitUntilElementIsNoLongerPresent(WebElement element)
            throws NoSuchElementException, InterruptedException {
        waitUntilJqueryIsDone();
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
        waitUntilElementIsNoLongerDisplayed(element, 5000);
    }
    public void waitUntilElementIsDisplayed(WebElement element)
            throws InterruptedException {
        int timeout=20000;
        int poll = 0;
        try {
            do {
                if (element != null) {
                    if (element.isDisplayed()) {
                        return;
                    }
                }
                Thread.sleep(200);
                poll += 200;
            } while (poll < timeout);
            System.out.println("Element " + element + " still not displayed - Timeout reached");
        } catch (NoSuchElementException e) {
            System.out.println("There is no such element: " + element);
        }
    }


    public void waitUntilElementIsDisplayed(WebElement element, int timeout)
            throws InterruptedException {
        int poll = 0;
        try {
            do {
                if (element != null) {
                    if (element.isDisplayed()) {
                        return;
                    }
                }
                Thread.sleep(200);
                poll += 200;
            } while (poll < timeout);
            System.out.println("Element " + element + " still not displayed - Timeout reached");
        } catch (NoSuchElementException e) {
            System.out.println("There is no such element: " + element);
        }
    }

    public void waitUntilElementIsNoLongerDisplayed(WebElement element, int timeout)
            throws InterruptedException {
        int poll = 0;
        try {
            do {
                if (!element.isDisplayed()) {
                    return;
                }
                Thread.sleep(200);
                poll += 200;
            } while (poll < timeout);
            System.out.println("Element " + element + " still displayed - Timeout reached");
        } catch (NoSuchElementException e) {
            System.out.println("There is no such element: " + element);
        }
    }

    public void scrollElementIntoView(String attribute) {

    }

    public void click(boolean contains) {

    }
}