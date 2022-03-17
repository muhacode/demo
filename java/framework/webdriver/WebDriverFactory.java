package framework.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    protected static WebDriver driver;
    private ResourceBundle BROWSER_PROPERTIES;

    //Creating Webdriver instance
    public WebDriverFactory(ResourceBundle BROWSER_PROPERTIES) {
        this.BROWSER_PROPERTIES = BROWSER_PROPERTIES;
        initWebDriver();
    }


    private void initWebDriver() {
        ChromeOptions options = new ChromeOptions();
        String chromepath = BROWSER_PROPERTIES.getString("CHROMEPATH");
        //


        switch (BROWSER_PROPERTIES.getString("BROWSER")) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", BROWSER_PROPERTIES.getString("GECKODRIVER"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", BROWSER_PROPERTIES.getString("CHROMEDRIVER"));
                //options.setBinary(chromepath);
                driver = new ChromeDriver();
                break;
            case "chromium":
                options.setBinary(chromepath);
                options.addArguments("--proxy-server=socks5://127.0.0.1:9999"); //for SSH tunnel
                driver = new ChromeDriver(options);
                break;
            case "headless":
                options.setBinary(chromepath);
                options.setHeadless(true);
                driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // Returns the driver instance
    public WebDriver getDriver() {
        return driver;
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    //Terminates the driver instance
    public void quitDriver() {
        driver.quit();
    }
}
