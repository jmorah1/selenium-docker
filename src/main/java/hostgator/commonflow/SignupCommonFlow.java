package hostgator.commonflow;

import java.util.Random;

import hostgator.pages.signup.SignupPage;

import hostgator.util.StaticData;
import org.openqa.selenium.WebDriver;

public class SignupCommonFlow extends SignupPage {

	Random random    = new Random();
	SignupPage signup= new SignupPage(driver);

    public SignupCommonFlow(WebDriver driver) {
        super(driver);
    }

    public void enterEmailAndConfirm(String packageName) {
        String email = "hgtest"+random.nextInt(100000)+packageName +random.nextInt(100000)+ "@endurance.com";
        signup.enterEmail(email);
        signup.enterConfirmEmail(email);
    }

    public void checkTOSandCheckout() throws InterruptedException {
        signup.WaitForSummaryTable();
        signup.checkTos1();
        signup.clickCheckout();
    }

    public void enterCredirCardInfo() {
        signup.enterCreditCardName(StaticData.TEST_CREDIT_CARD_NAME);
        signup.enterCreditCardNumber(StaticData.TEST_CREDIT_CARD_NUMBER);
        signup.enterCreditCardCVV(StaticData.TEST_CREDIT_CARD_CVV);
    }

    public void enterBillingInfo() {
        signup.enterFirstName(StaticData.FIRST_NAME);
        signup.enterLastName(StaticData.LAST_NAME);
        signup.enterPhone(StaticData.PHONE);
        signup.enterAddress1(StaticData.ADDRESS1);
        signup.enterAddress2(StaticData.ADDRESS2);
        signup.enterCity(StaticData.CITY);
        signup.enterZipCode(StaticData.ZIP);
    }

    public void sharedPackageCheckTOSandCheckoutTwice() {
        signup.WaitForSummaryTable();
        signup.checkTos1();
        signup.clickCheckout();
        signup.clickCheckout(); //Clicking checkout again cause first click loads up and does nothing
    }
}
