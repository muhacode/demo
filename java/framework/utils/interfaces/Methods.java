package framework.utils.interfaces;


import framework.utils.listeners.TestNGListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Methods extends TestNGListener {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait_20, wait_30, wait_40, wait_50;

    @Deprecated
    protected Methods(WebDriver webDriver) {

        this.driver = webDriver;
        this.actions = new Actions(driver);
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.wait_20 = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.wait_30 = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.wait_40 = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.wait_50 = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

}
