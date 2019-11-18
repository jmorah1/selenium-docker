package hostgator;

import hostgator.commonflow.SignupCommonFlow;
import hostgator.pages.signup.SignupPage;
import hostgator.driver.TestDriver;
import hostgator.util.StaticData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class OWPSignup extends TestDriver {

	private static Logger log = LogManager.getLogger(OWPSignup.class.getName());
    SignupPage signup;
    SignupCommonFlow signupFlow;

	@BeforeTest
	public void initialize() throws IOException {
		initializeDriver();
//		driver.manage().window().maximize();
		driver.get(prop.getProperty(mvnPassedEnvironment())+StaticData.WORDPRESS_PKG);
	}

	@Test(groups  = {"SmokeTest", "SignupRegression"})
	public void HGQ_900_wordpress_existing_customer_existing_domain_cc() throws InterruptedException, IOException {
		signup=new SignupPage(driver);
		signupFlow =new SignupCommonFlow(driver);
		signup.clickIAlreadyOwnThisDomain();
		signup.enterExistingDomain(StaticData.DOMAIN_NAME, "owp");
		signup.billingDropdown(0);
		signup.enterPin(StaticData.PIN);
		signup.ExistingEmailSignIn(StaticData.SHARED_DEFAULT_EMAIL, StaticData.DEFAULT_PASSWORD);
		signupFlow.checkTOSandCheckout();
		signup.verifyPaymentComplete();
	}

	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Closing Driver");
		driver=null;
	}
}
