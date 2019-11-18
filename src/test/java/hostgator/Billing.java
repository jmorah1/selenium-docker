package hostgator;

import hostgator.pages.portal.billing.BillingHistoryPage;
import hostgator.pages.portal.HomePage;
import hostgator.pages.portal.LoginPage;
import hostgator.driver.TestDriver;
import hostgator.util.StaticData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.IOException;

public class Billing extends TestDriver {

    private static Logger log = LogManager.getLogger(Billing.class.getName());

    @BeforeMethod
    public void beforeMethod() throws IOException {
        initializeDriver();
        driver.manage().window().maximize();
    }

    @Test(groups  = {"SmokeTest", "BillingRegression"}) //HGQ-1127
    public void HGQ_1127_verify_billing_history() {

        driver.get(prop.getProperty(mvnPassedEnvironment()));
        log.info("Navigated to Portal page");

        LoginPage portalLogin=new LoginPage(driver);
        HomePage homePage=new HomePage(driver);
        BillingHistoryPage billingHistoryPage = new BillingHistoryPage(driver);

        portalLogin.portalLogin(StaticData.SHARED_DEFAULT_EMAIL, StaticData.PORTAL_PASSWORD);
        homePage.navigateToBillingHistory();
        billingHistoryPage.printInvoiceNumbers();
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
		driver.close();
		log.info("Closing Driver");
    }

    @AfterClass
    public void teardown() {
        driver=null;
    }
}
