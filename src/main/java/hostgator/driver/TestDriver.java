package hostgator.driver;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestDriver {

    public WebDriver driver;
    public Properties prop;
    private static Logger log = LogManager.getLogger(TestDriver.class.getName());

    public WebDriver initializeDriver() throws IOException
    {
        prop=new Properties();
        FileInputStream fis2=new FileInputStream(System.getProperty("user.dir")+"/Environments.properties");
        prop.load(fis2);

        String os          = System.getProperty("os.name");
        String browserName = System.getProperty("browser");

        if (browserName==null){
//			browserName = "chromeheadless"; //commenting out for development
//			log.warn("No browser variable passed. Running Chrome Headless");
            browserName = "chrome";
        }

        if(os.contains("Mac")) {
            if (browserName.contains("chrome")) {

                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/webDrivers/Mac/chromedriver");

                ChromeOptions options=new ChromeOptions();
                if (browserName.contains("headless")) {
                    options.addArguments("--headless");
                }

                DesiredCapabilities capabilities = new DesiredCapabilities();
                BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy

                Proxy seleniumProxy = getSeleniumProxy(proxy);
                capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

                driver = new ChromeDriver(capabilities); //not passing options yet
                log.info("Mac Chrome Driver initialized");
            }
        }

        if(os.contains("Windows")) {
            if (browserName.contains("chrome")) {

                System.setProperty("webdriver.chrome.driver", "./webDrivers/Windows/chromedriver.exe");

                ChromeOptions options=new ChromeOptions();
                if (browserName.contains("headless")) {
                    options.addArguments("--headless");
                }

                DesiredCapabilities capabilities = new DesiredCapabilities();
                BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy

                Proxy seleniumProxy = getSeleniumProxy(proxy);
                capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

                driver = new ChromeDriver(capabilities);
                log.info("Windows Chrome Driver initialized");

            }
        }

        if(os.contains("Linux")) {
            if (browserName.contains("chrome")) {

                System.setProperty("webdriver.chrome.driver", "./webDrivers/Linux/chromedriver");

                ChromeOptions options=new ChromeOptions();
                if (browserName.contains("headless")) {
                    options.addArguments("--headless");
                }

                DesiredCapabilities capabilities = new DesiredCapabilities();
                BrowserMobProxy proxy = getProxyServer(); //getting browsermob proxy

                Proxy seleniumProxy = getSeleniumProxy(proxy);
                capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

                driver = new ChromeDriver(capabilities);
                log.info("Linux Chrome Driver initialized");
            }
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;

    }

    public Proxy getSeleniumProxy(BrowserMobProxy proxyServer) {
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
        try {
            String hostIp = Inet4Address.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + proxyServer.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + proxyServer.getPort());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            Assert.assertTrue(false,"invalid Host Address");
        }
        return seleniumProxy;
    }

    public BrowserMobProxy getProxyServer() {
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start();
        proxy.addRequestFilter((request, contents, messageInfo) -> {
            request.headers().add("X-GATOR-REQUESTOR", "jmorah-test");
            request.headers().add("X-GATOR-AUTH", "87aa0de52ec992880513105308e3c990"); //portal10
//			request.headers().add("X-GATOR-AUTH", "de0bc963131a815c9ab58d95bd46e790");

            return null;
        });
        return proxy;
    }

    //Takes screenshot of section of page (section in view)
    public void getScreenshot(String result) throws IOException {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File(System.getProperty("user.dir")+"/Screenshots/"+result+"-Screenshot1.png")); ///Users/jmorah/git/automation/Screenshots
    }

    public void analyzeLog(String result) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println("\n\nConsole logs for " +result + " testcase - \n" + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage() + "\nEnd Console logs \n\n");
            //do something else with the data
        }
    }

    public String mvnPassedEnvironment() {
        String defaultEnvironment = "qaAutoMaintenance";
        String environmentVariable = System.getProperty("environment");
        if (environmentVariable==null) {
            environmentVariable = defaultEnvironment;
            log.info("No Environment variable passed, defaulting to " +defaultEnvironment);
        }
        return environmentVariable;
    }

    public void waitUntilID(int waitTime, String id) {

        @SuppressWarnings("deprecation")
        WebDriverWait w = new WebDriverWait(driver, waitTime);
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }
}