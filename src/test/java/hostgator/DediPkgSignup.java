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

public class DediPkgSignup extends TestDriver {
	private static Logger log = LogManager.getLogger(DediPkgSignup.class.getName());
    SignupPage signup;
    SignupCommonFlow signupFlow;

	@BeforeTest
	public void initialize() throws IOException {
		initializeDriver();
		driver.get(prop.getProperty(mvnPassedEnvironment())+StaticData.DEDI_PKG);
		log.info("Navigated to dedi pkg signup page");
	}

	@Test(groups  = {"SmokeTest", "SignupRegression"})
	public void HGQ_899_dedi_topright_signin_existing_customer_existing_domain_pp() throws InterruptedException, IOException {
		signup=new SignupPage(driver);
		signupFlow =new SignupCommonFlow(driver);

		signup.clickIAlreadyOwnThisDomain();
		signup.enterExistingDomain(StaticData.DOMAIN_NAME, "dedi");
		signup.billingDropdown(0);
		signup.TopRightSignIn(StaticData.SHARED_DEFAULT_ACCOUNT);
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
