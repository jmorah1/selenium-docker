package hostgator;

import hostgator.pages.domain.CartCheckout;
import hostgator.pages.domain.DomainRegistration;
import hostgator.driver.TestDriver;
import hostgator.util.StaticData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DomainPurchase extends TestDriver {

	private static Logger log = LogManager.getLogger(DomainPurchase.class.getName());
	DomainRegistration domainRegistration;
	CartCheckout cartCheckout;

	@BeforeTest
	public void initialize() throws IOException {
		initializeDriver();
		driver.get(prop.getProperty(mvnPassedEnvironment())+StaticData.DOMAIN_PURCHASE);
	}

	//This test is incomplete
	@Test(groups  = {"SmokeTest", "SignupRegression"})
	public void HGQ_1129_new_customer_new_domain_cc() throws InterruptedException, IOException {
		domainRegistration =new DomainRegistration(driver);
		cartCheckout =new CartCheckout(driver);

		domainRegistration.enterDomainAndSearch();
		domainRegistration.clickContinueCheckoutButton();
		cartCheckout.enterEmail();
		cartCheckout.enterConfirmPassword(StaticData.DEFAULT_PASSWORD);
		cartCheckout.clickContinue();
		cartCheckout.enterBillingInfo();
		cartCheckout.enterCCInfo();
		cartCheckout.clickContinueToCheckout();
		cartCheckout.acceptTOSAndPlaceOrder();
	}

	@AfterTest
	public void teardown() {
		driver.close();
		log.info("Closing Driver");
		driver=null;
	}
}