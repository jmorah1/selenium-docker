package hostgator.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestDriver3 {

	public WebDriver driver;
	public Properties prop;
	private static Logger log = LogManager.getLogger(TestDriver3.class.getName());

	public void initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/Environments.properties");
		prop.load(fis);

		String os = System.getProperty("os.name");
		String host = "localhost";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--disable-extensions");
		options.addArguments("--auto-open-devtools-for-tabs");
//		capabilities.setCapability("chrome.binary", "<Path to binary>");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

		if (System.getProperty("BROWSER") != null && System.getProperty("browser").equalsIgnoreCase("firefox")) {
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		}

		if (System.getProperty("host") != null) {
			host = System.getProperty("HUB_HOST");
		}

		String completeUrl = "http://" + host + ":4444/wd/hub";
		this.driver = new RemoteWebDriver(new URL(completeUrl), capabilities);

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