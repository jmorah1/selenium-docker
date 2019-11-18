package hostgator.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;
    private static Logger log = LogManager.getLogger(TestDriver2.class.getName());

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
        // BROWSER => chrome / firefox
        // HUB_HOST => localhost / 10.0.1.3 / hostname

        String host = "localhost";
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            capabilities.setCapability(CapabilityType.BROWSER_NAME,"firefox");
        }else{
            capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();
        String completeUrl = "http://" + host + ":4444/wd/hub";
        capabilities.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl), capabilities);
        log.info("Chrome remote Driver is initialized");
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
        log.info("Closing remote Driver");
    }
}