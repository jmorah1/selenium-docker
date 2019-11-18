package hostgator.pages.signup;

import hostgator.driver.TestDriver;
import hostgator.pages.paypal.PayPalLogin;
import hostgator.util.StaticData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class SignupPage extends TestDriver {

	Date date= new Date();
	Random random = new Random();

	private static Logger log = LogManager.getLogger(SignupPage.class.getName());

	public SignupPage(WebDriver driver)
	{
		this.driver=driver;
	}

	//Page Elements
	//region Domain Section Elements
	private By _iOwnThisDomain = By.id("old_domain_tab");
	private By _domainTextField = By.id("domain_field");
	//endregion

	//region Hosting Plan Elements
	private By _tldSelect = By.id("tld_select");
	private By _billingDropdown = By.id("billing_cycle");
	private By _usernameTextField = By.xpath("//*[@id=\"username\"]");
	private By _pinTextField = By.id("new_pin");
	//endregion

	//region Billing Info Section Elements
	private By _emailTextField = By.id("new-email");
	private By _confirmRmailField = By.id("new-email-confirm");
	private By _firstNameField = By.id("new-first-name");
	private By _lastNameField = By.id("new-last-name");
	private By _phoneNumberField = By.id("hphone");
	private By _address1Field = By.id("new-address1");
	private By _address2Field = By.id("new-address2");
	private By _cityField = By.id("new-city");
	private By _zipCodeField = By.id("zip_code");
	//endregion

	//region Credit Card Payment Type Elements
	private By _ccCardNameField = By.id("new-name-on-card");
	private By _ccNumberField = By.id("new_cc");
	private By _ccCvvField = By.id("security_code_new");
	//endregion

	//region Checkout Section Elements
	private By _tosCheckbox1 = By.xpath("//*[@id=\"validation-form\"]/div[7]/div/div/div/p[3]/div/ins");
	private By _tosCheckbox2 = By.id("tos-agree");  //By.xpath("//*[@id=\"validation-form\"]/div[7]/div/div/div/p[3]/div/ins");
	private By _tosCheckbox3 = By.className("iCheck-helper"); //By.id("tos-agree");  //By.xpath("//*[@id=\"validation-form\"]/div[7]/div/div/div/p[3]/div/ins");
	private By _checkoutButton = By.id("final-checkout");
	//endregion

	//region Top Right Sign-in Elements
	private By _regflowTopRightEmailField = By.id("sign-in-un");
	private By _regflowTopRightPasswordField = By.id("sign-in-pw");
	private By _regflowTopRightSignintext = By.linkText("Sign In!");
	private By _regFlowTopRightSigninButton = By.xpath("//*[@id=\"signup-container\"]/section/div[1]/div/div/div/div[2]/a");
	private By _logoutlink = By.xpath("//*[@id=\"signup-container\"]/section/div[1]/div/div/a");
	//endregion

	//region Existing Account Sign-in Elements
	private By _existingAccountPasswordField = By.id("existing-password");
	private By _existingAccountLoginButton = By.id("section-login");
	//endregion

	//region PayPal Elements
	private By _usePayPalTab = By.id("paypal_tab");
	private By _paypalRadio = By.id("paypal");
	//endregion
	//End of Page Elements

	//Page Functions

	//region Top Right Sign-in Methods
	public void clickTopRightSignintext()
	{
		driver.findElement(_regflowTopRightSignintext).click();
		log.info("Clicked Top Right Sign in Button");
	}

	public void enterTopRightEmail(String email)
	{
		driver.findElement(_regflowTopRightEmailField).sendKeys(email);
		log.info("Entered Existing Email");
	}

	public void enterTopRightPassword(String password)
	{
		driver.findElement(_regflowTopRightPasswordField).sendKeys(password);
		log.info("Clicked Password");
	}

	public void clickTopRightSigninButton()
	{
		driver.findElement(_regFlowTopRightSigninButton).click();
		log.info("Clicked Signin Button");
	}
	//endregion

	//region Login With Existing Account: Middle Sign in Methods
	public void enterExistingAccountEmail(String email)
	{
		driver.findElement(_emailTextField).sendKeys(email);
		log.info("Entered Existing Email");
	}

	public void enterExistingAccountPassword(String existingAccountPassword)
	{
		driver.findElement(_existingAccountPasswordField).sendKeys(existingAccountPassword);
		log.info("Entered Existing Account Password");
	}

	public void clickExistingAccountLoginButton()
	{
		driver.findElement(_existingAccountLoginButton).click();
		log.info("Clicked on Logion Button");
	}
	//endregion

	//region Choose a Domain Methods
	public void clickIAlreadyOwnThisDomain()
	{
		driver.findElement(_iOwnThisDomain).click();
		log.info("Switched to I Already Own This Domain Tab");
	}

	public void enterStoredExistingDomain(String domain)
	{
		driver.findElement(_domainTextField).sendKeys(domain);
		log.info("Entered and Stored Existing Domain");
	}

	public void enterExistingDomain(String domain, String packageName)
	{
		String existingDomain = domain+random.nextInt(10000)+packageName+random.nextInt(100000)+".com";
		driver.findElement(_domainTextField).sendKeys(existingDomain);
		driver.findElement(_domainTextField).sendKeys(Keys.TAB);
		verifyDomainIsValid();
		log.info("Entered and Verified Existing Domain");
	}

	public void enterDomain(String domain, String packageName)
	{
		driver.findElement(_domainTextField).sendKeys(domain+random.nextInt(10000)+packageName+random.nextInt(10000)); //random.nextInt(10000) //date.getTime());
		log.info("Entered New Domain");
	}
	//endregion

	public void verifyDomainIsValid()
	{
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.withMessage("No Valid Domain search results\n");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"results-area\"]/div[1]/div/div/div/div[1]/label/span[2]"), "PRIMARY DOMAIN"));
	}

	//region Choose a Hosting Plan Methods
	public void tldDropdown(int index)
	{
		Select s=new Select(driver.findElement(_tldSelect));
		s.selectByIndex(index);
		log.info("Selected TLD");
	}

	public void billingDropdown(int index)
	{
		Select s=new Select(driver.findElement(_billingDropdown));
		s.selectByIndex(index);
		log.info("Selected billing Term");
	}


	public void enterUsername(String username)
	{
		driver.findElement(_usernameTextField).sendKeys(username+random.nextInt(50));
		log.info("Entered Username");
	}

	public void enterPin(String pin)
	{
		driver.findElement(_pinTextField).sendKeys(pin);
		log.info("Entered Pin");
	}
	//endregion

	//region Enter Your Billing Info Methods
	public void enterEmail(String email)
	{
		driver.findElement(_emailTextField).sendKeys(email);
		log.info("Entered New Email");
	}

	public void enterConfirmEmail(String email)
	{
		driver.findElement(_confirmRmailField).sendKeys(email);
		log.info("Confirmed New Email");
	}

	public void enterFirstName(String firstName)
	{
		driver.findElement(_firstNameField).sendKeys(firstName);
		log.info("Entered First Name");
	}

	public void enterLastName(String lastName)
	{
		driver.findElement(_lastNameField).sendKeys(lastName);
		log.info("Entered Last Name");
	}

	public void enterPhone(String phone)
	{
		driver.findElement(_phoneNumberField).sendKeys(phone);
		log.info("Entered Phone #");
	}

	public void enterAddress1(String address1)
	{
		driver.findElement(_address1Field).sendKeys(address1);
		log.info("Entered Adress1");
	}

	public void enterAddress2(String address2)
	{
		driver.findElement(_address2Field).sendKeys(address2);
		log.info("Entered Address2");
	}

	public void enterCity(String city)
	{
		driver.findElement(_cityField).sendKeys(city);
		log.info("Entered Billing City");
	}

	public void enterZipCode(String zipcode)
	{
		driver.findElement(_zipCodeField).sendKeys(zipcode);
		log.info("Entered Billing ZipCode");
	}
	//endregion


//	public void enterExistingEmail(String domain, String packageName)
//	{
//		driver.findElement(domain_text_field).sendKeys(domain+random.nextInt(10000)+packageName+random.nextInt(100000)+".com");
//	}

	//region Payment Type: PayPal Methods
	public void clickPayPalTab()
	{
		driver.findElement(_usePayPalTab).click();
		log.info("Switched to PayPal Tab");
	}

	public void clickPaypalPaymentType() {
		driver.findElement(_paypalRadio).click();
		log.info("Selected Paypal Payment type");
	}
	//endregion

	//region Payment Type: Credit Card Methods
	public void enterCreditCardName(String ccName)
	{
		driver.findElement(_ccCardNameField).sendKeys(ccName);
		log.info("Entered CC Name");
	}

	public void enterCreditCardNumber(String cc)
	{
		driver.findElement(_ccNumberField).sendKeys(cc);
		log.info("Entered CC #");
	}

	public void enterCreditCardCVV(String  cvv)
	{
		driver.findElement(_ccCvvField).sendKeys(cvv);
		log.info("Entered CC CVV");
	}
	//endregion

	//region Review Order Details and Checkout Methods
	public void checkTos1()
	{
		driver.findElement(_tosCheckbox1).click();
		log.info("Clicked TOS");
	}

	public void tosCheckbox3() //works for shared pkg
	{
		driver.findElement(_tosCheckbox3).click();
		log.info("Clicked TOS");
	}

	public void clickCheckout()
	{
		driver.findElement(_checkoutButton).click();
		log.info("Clicked Checkout Button");
	}
	//endregion

	public void verifyPaymentComplete() throws InterruptedException {
		int waitTime = 30;
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(
				ExpectedConditions.or(
						ExpectedConditions.urlContains("/signup/complete/"),
						ExpectedConditions.urlContains("billing/invoice/pay/select"),
						ExpectedConditions.urlContains("paypal")
				)
		);

		if (driver.getCurrentUrl().contains("billing/invoice/pay/select")) {
			log.warn("Payment error");

			List<WebElement> paypalError = driver.findElements(By.xpath("//*[@id=\"gbclient\"]/div[5]/div/div[2]/div[1]/div/div/p"));

			if ( paypalError.size() > 0 )  {
				log.info("Error message: "+paypalError.get(0).getText());
				Assert.fail("Issue with paypal");
			}
		} else if (driver.getCurrentUrl().contains("/signup/complete/")) {
			log.info("Signup Complete");
		} else if (driver.findElement(By.id("email")).isDisplayed()) { //replace this with paypallink when its working

			paypalLogin();

		} else if (true){
			Thread.sleep(99999999);
		}
	}

//		Assert.assertTrue(driver.getCurrentUrl().contains("/signup/complete/"), "Waited for "+ waitTime +". Did not make it to signup/complete page");
//
//		try{
//			Assert.assertEquals(...,...);
//		}catch(AssertionError e){
//			Log error;
//			Takescreenshot;
//		}
//
//		log.info("Signup Complete");
//	}

	public void paypalLogin() throws InterruptedException {
		waitUntilID(20, "email");
		PayPalLogin paypalLogin=new PayPalLogin(driver);

		paypalLogin.enterPayPalEmail(StaticData.PAYPAL_EMAIL);
		paypalLogin.clickPayPalNextButton();
		paypalLogin.enterPayPalPassword(StaticData.PAYPAL_PASSWORD);
//		paypalLogin.unCheckStayLoggedIn();
		paypalLogin.clickPayPalLoginButton();
//		Boolean notNow = driver.findElements(By.id("notNowLink")).size() > 0 ;
//		if (notNow) {
//
//			driver.findElement(By.id("notNowLink")).click();
//
//		}
//		paypalLogin.clickPayPalContinueButton();
		paypalLogin.clickPayPalContinueButton2();
//		paypalLogin.clickPaypalAgreeAndContinuebutton();
		paypalLogin.clickPaypalAgreeAndContinuebutton2();
	}

	public void TopRightSignIn(String email) {
		clickTopRightSignintext();
		enterTopRightEmail(email);
		enterTopRightPassword(StaticData.PORTAL_PASSWORD);
		clickTopRightSigninButton();
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.withMessage("Login Failed");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(_logoutlink, "Logout"));

	}

	public void ExistingEmailSignIn(String existingEmail, String password) throws InterruptedException {
		enterExistingAccountEmail(existingEmail);

		WebElement webElement = driver.findElement(By.id("new-email"));
		webElement.sendKeys(Keys.TAB);

		@SuppressWarnings("deprecation")
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.visibilityOfElementLocated(_existingAccountPasswordField));

		enterExistingAccountPassword(password);
		clickExistingAccountLoginButton();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("new_cc_tab")));
	}

	public void WaitForSummaryTable() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement summaryTable = driver.findElement(By.id("summary-table"));
				String classAttribute = summaryTable.getAttribute("class");

//					System.out.println("\n"+classAttribute+"\n");
				if(classAttribute.contains("processing")) {
					log.info("Summary page is still loading: "+classAttribute);
					return false;
				}
				else
					return true;
			}
		});
	}
}
