package hostgator.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestDriver2 {

	public WebDriver driver;
	public Properties prop;
	private static Logger log = LogManager.getLogger(TestDriver2.class.getName());

	@BeforeTest
	public void initializeDriver(ITestContext ctx) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/Environments.properties");
		prop.load(fis);

		String os = System.getProperty("os.name");
		String host = "localhost";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");

		if (System.getProperty("BROWSER") != null && System.getProperty("browser").equalsIgnoreCase("firefox")) {
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		}

		if (System.getProperty("host") != null) {
			host = System.getProperty("HUB_HOST");
		}

		String completeUrl = "http://" + host + ":4444/wd/hub";

		String testName = ctx.getCurrentXmlTest().getName();



		capabilities.setCapability("name", testName);


		this.driver = new RemoteWebDriver(new URL(completeUrl), capabilities);
		log.info("Chrome remote Driver is initialized");
	}

}
