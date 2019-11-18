package hostgator;

import java.io.IOException;

import hostgator.commonflow.SignupCommonFlow;
import hostgator.driver.BaseTest;
import hostgator.pages.signup.SignupPage;
import org.apache.logging.log4j.*;
import org.testng.annotations.*;

import hostgator.driver.TestDriver;
import hostgator.util.StaticData;

public class SharedPkgSignup extends BaseTest {

	private static Logger log = LogManager.getLogger(SharedPkgSignup.class.getName());
    SignupPage signup;
	SignupCommonFlow signupFlow;

	@Test(groups  = {"SmokeTest", "SignupRegression"}) //
	public void HGQ_898_shared_new_customer_new_domain_cc() throws IOException, InterruptedException {
		driver.get("https://portal10.hostgator.com"+StaticData.SHARED_PKG);

		signupFlow =new SignupCommonFlow(driver);
		signup     =new SignupPage(driver);
		signup.enterDomain(StaticData.DOMAIN_NAME, "sharedpackage");
		signup.tldDropdown(0);
		signup.billingDropdown(0);
		signup.enterUsername(StaticData.USERNAME);
		signup.enterPin(StaticData.PIN);
		signupFlow.enterEmailAndConfirm("shared");
		signupFlow.enterBillingInfo();
		signupFlow.enterCredirCardInfo();
//		signupFlow.sharedPackageCheckTOSandCheckoutTwice();
//		signup.verifyPaymentComplete();
	}
}
