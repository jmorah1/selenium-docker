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

public class ResellerSignup extends TestDriver {

	private static Logger log = LogManager.getLogger(ResellerSignup.class.getName());
    SignupPage signup;
    SignupCommonFlow signupFlow;

	@BeforeTest
	public void initialize() throws IOException {
		initializeDriver();
//		driver.manage().window().maximize();
		driver.get(prop.getProperty(mvnPassedEnvironment())+StaticData.RESELLER_PKG);
	}

	@Test(groups  = {"SmokeTest", "SignupRegression"})
	public void HGQ_1125_reseller_existing_customer_top_right_signin_existing_domain_pp() throws InterruptedException {
		signup=new SignupPage(driver);
		signupFlow = new SignupCommonFlow(driver);

		signup.clickIAlreadyOwnThisDomain();
		signup.enterExistingDomain(StaticData.DOMAIN_NAME, "reseller");
		signup.billingDropdown(0);
		signup.TopRightSignIn(StaticData.SHARED_DEFAULT_EMAIL);
		signup.enterUsername(StaticData.USERNAME);
		signup.clickPayPalTab();
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
