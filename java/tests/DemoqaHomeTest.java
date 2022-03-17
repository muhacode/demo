package tests;

import framework.utils.interfaces.ITestTemplate;
import framework.utils.interfaces.TestTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import project.demoqa.pages.DemoqaHome;

import java.util.concurrent.TimeUnit;

import static framework.reporting.ExtentTestFactory.getTest;


public class DemoqaHomeTest extends TestTemplate implements ITestTemplate {
    private WebDriver driver;

    @BeforeClass
    public void preconditions() throws Throwable {
        getTest().info("This test serves to verify if the main page links and components are functional and shown properly");
        this.initializeBundels();

        getTest().info("Enter Demoqa");
        driver.navigate().to("https://demoqa.com");

    }

    @Test
    public void test() throws Throwable {
        DemoqaHome demoqa = new DemoqaHome(driver);
        demoqa.firstTest();
        Thread.sleep(4000);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);

        getTest().pass("Test successful").info(addScreenshot());

    }

    @AfterClass(alwaysRun = true)
    public void postconditions() throws Throwable {
        getTest().info("Test finished");
    }
    private void initializeBundels() {
        driver = webDriverFactory.getDriver();
        Actions actions = new Actions(driver);

//        methods = new PageCommonMethods();
    }
}
