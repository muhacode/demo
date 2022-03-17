package project.demoqa.utils;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.sukgu.Shadow;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import static framework.reporting.ExtentTestFactory.getTest;

public class PageCommonMethods {

    private static final int findElementThroughJSTimeout = 6000;
    public static JsonArray jsonArray = new JsonArray();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public boolean waitUntilLabelShown(WebDriver driver, String label){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String text = "//h3[contains(text(),'" + label + "')]";
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(text)));
            return true;
        } catch (NullPointerException | ElementNotFoundException e){
            getTest().fail("Element not found");
            return false;
        }
    }

   public static JsonObject toJson(File file) {
        try {
            return gson.fromJson(new FileReader(file), JsonObject.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static WebElement getShadowElement(WebDriver driver, By... by)
            throws NoSuchElementException {
        return getShadowElement(driver, findElementThroughJSTimeout, by);
    }

    /**
     * Retrieves the shadow element given the path in By's.
     */
    public static WebElement getShadowElement(WebDriver driver, int mTimeout, By... by)
            throws NoSuchElementException {
        int timeConsumed = 0;

        do {
            try {
                WebElement[] root = new WebElement[by.length];
                WebElement[] shadowRoot = new WebElement[by.length];
                for (int i = 0; i <= by.length - 2; i++) {
                    if (i == 0) {
                        root[i] = driver.findElement(by[i]);
                        shadowRoot[i] = expandRootElement(root[i], driver);
                    } else {
                        root[i] = shadowRoot[i - 1].findElement(by[i]);
                        shadowRoot[i] = expandRootElement(root[i], driver);
                    }
                }
                return shadowRoot[by.length - 2].findElement(by[by.length - 1]);
            } catch (NullPointerException e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                timeConsumed += 100;
            }
        } while (timeConsumed < mTimeout);
        throw new NoSuchElementException("Could not find element: " + Arrays.toString(by));
    }

    public static WebElement getElementByShadowSearch(WebDriver driver, String elementId) {
        Shadow shadow = new Shadow(driver);
        return shadow.findElement(elementId);

    }

    /**
     * This method retrieves an element from the DOM using its javascript path.
     */
    public static WebElement getElementUsingJSPath(WebDriver driver, String jsPath) {
        int timeout = 0;
        do {
            try {
                return (WebElement) ((JavascriptExecutor) driver).executeScript("return " + jsPath);
            } catch (NullPointerException e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
                timeout += 200;
            }

        } while (timeout < findElementThroughJSTimeout);
        throw new NoSuchElementException("Could not find element: " + jsPath);
    }

    /**
     * This method retrieves an element from the DOM using its javascript path under a given element
     */
    public static WebElement getElementUsingJSPath(
            WebDriver driver, WebElement element, String jsPath) {
        int timeout = 0;
        String script = "return arguments[0]." + jsPath;
        do {
            try {
                return (WebElement) ((JavascriptExecutor) driver).executeScript(script, element);
            } catch (NullPointerException e) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
                timeout += 200;
            }

        } while (timeout < findElementThroughJSTimeout);
        throw new NoSuchElementException("Could not find element: " + jsPath);
    }

    /**
     * Executes JS code in Browser and returns the output in String
     */
    public static Boolean executeJSreturnBoolean(WebDriver driver, String js) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(js);
    }

    /**
     * Executes JS code in Browser and returns the output in String
     */
    public static String executeJSreturnString(WebDriver driver, String js, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(js, element);
    }

    /**
     * Executes JS code in Browser and returns the output in Long
     */
    public static long executeJSreturnLong(WebDriver driver, String js, WebElement element)
            throws InterruptedException {
        long result;
        int timeout = 5000;
        int poll = 0;
        do {
            result = (long) ((JavascriptExecutor) driver).executeScript(js, element);
            if (result > 0) {
                break;
            }
            poll += 500;
            Thread.sleep(500);
        } while (poll <= timeout);
        return result;
    }

    /**
     * Scrolls down the active element by the given quantity.
     */
    public static void scrollDown(WebDriver driver, int quantity) {
        WebElement activeElement = driver.switchTo().activeElement();
        for (int i = 0; i < quantity; i++) {
            activeElement.sendKeys(Keys.PAGE_DOWN);
        }
    }

    private static WebElement expandRootElement(WebElement element, WebDriver driver) {
        return (WebElement)
                ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
    }
}
