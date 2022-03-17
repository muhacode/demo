package framework.utils.essentials;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import framework.utils.common.CommonMethods;
import framework.webdriver.DriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Wait {
    private final String Xloading="//*[@id=\"loadingcontent\"]";
    protected WebDriver driver;
    protected DriverActions actions;
protected WebDriverWait wait_loading;
protected WebDriverWait wait_loading_2;

    public Wait(WebDriver driver) {
        this.driver = driver;
        this.actions=new DriverActions(driver);
        this.wait_loading=new WebDriverWait(driver,100);
        this.wait_loading_2=new WebDriverWait(driver,2);
    }

    protected WebDriverWait wait_20() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),20000);
        return new WebDriverWait(driver, 20);
    }

    protected WebDriverWait wait_30() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),30000);
        return new WebDriverWait(driver, 30);
    }

    protected WebDriverWait wait_2() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),2000);
        return new WebDriverWait(driver, 2);
    }


    protected WebDriverWait wait_40() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),40000);
        return new WebDriverWait(driver, 40);
    }

    protected WebDriverWait wait_5() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),5000);
        return new WebDriverWait(driver, 5);
    }


    protected WebDriverWait wait_50() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),50000);
        return new WebDriverWait(driver, 50);
    }

    protected WebDriverWait wait_10() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),10000);
        return new WebDriverWait(driver, 10);
    }

    protected WebDriverWait wait_60() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),60000);
        return new WebDriverWait(driver, 60);
    }

    protected WebDriverWait wait_70() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),70000);
        return new WebDriverWait(driver, 70);
    }

    protected WebDriverWait wait_80() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),80000);
        return new WebDriverWait(driver, 80);
    }

    protected WebDriverWait wait_90() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        actions.waitUntilElementIsNoLongerDisplayed( wait_loading.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Xloading))),90000);
        return new WebDriverWait(driver, 90);
    }


    protected Media addScreenshot() throws IOException, AWTException {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(CommonMethods.screenshotBase64(driver)).build();
    }
}
