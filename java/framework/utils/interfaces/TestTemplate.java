package framework.utils.interfaces;


import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.MediaEntityBuilder;
import framework.utils.common.CommonMethods;
import framework.webdriver.WebDriverFactory;

import org.testng.annotations.*;

import java.awt.*;
import java.io.IOException;
import java.util.ResourceBundle;

import static framework.reporting.ExtentReportFactory.getExtentReports;
import static framework.reporting.ExtentTestFactory.startTest;

public class TestTemplate {

    public static String jsPath;
    protected static WebDriverFactory webDriverFactory;
    protected ResourceBundle TESTDATA = null;
    protected ResourceBundle ENVIRONMENT = null;
    protected ResourceBundle BROWSER_PROPERTIES;

    @Parameters({"testdata", "testname", "env", "description", "browser", "path"})
    @BeforeSuite(alwaysRun = true)
    @Deprecated
    public void beforeSuit(@Optional("testdata") String testdata, @Optional("testname") String testname, @Optional("env") String env, @Optional("description") String description, @Optional("browser") String browser, @Optional("path") String path) throws Exception {
        if (!browser.equalsIgnoreCase("browser")) {
            this.BROWSER_PROPERTIES = ResourceBundle.getBundle("browser/" + browser);
            this.jsPath = BROWSER_PROPERTIES.getString("JSPATH");
            webDriverFactory = new WebDriverFactory(BROWSER_PROPERTIES);
        }
        startTest(testname, description);
        webDriverFactory.getDriver().navigate().to(path);
        webDriverFactory.getDriver().manage().window().maximize();


    }

    @Parameters({"testdata", "testname", "env", "description"})
    @BeforeTest
    public void beforeTest(@Optional("testdata") String testdata, String testname, @Optional("env") String env, @Optional("description") String description) {
        startTest(testname, description);
        if (!testdata.equalsIgnoreCase("testdata")) {
            this.TESTDATA = ResourceBundle.getBundle("testdata/" + testdata);
        }
        if (!testdata.equalsIgnoreCase("env")) {
            this.ENVIRONMENT = ResourceBundle.getBundle("env/" + env);
        }

    }

    /**
     * Ends the test
     */
    @Parameters({"browser"})
    @AfterTest(alwaysRun = true)
    public void endTest(@Optional("browser") String browser) {

    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        webDriverFactory.quitDriver();
        getExtentReports().flush();
    }

    protected Media addScreenshot() throws IOException, AWTException {
        return MediaEntityBuilder.createScreenCaptureFromBase64String(CommonMethods.screenshotBase64(webDriverFactory.getDriver())).build();
    }

}