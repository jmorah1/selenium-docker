package hostgator.pages.paypal;

import hostgator.util.StaticData;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PayPalLogin{

	private static Logger log = LogManager.getLogger(PayPalLogin.class.getName());
	private WebDriver driver;
	public PayPalLogin(WebDriver driver)
	{
		this.driver=driver;
	}

	private By _paypal_email_field    = By.id("email");
	private By _paypal_next_buttom    = By.id("btnNext");
	private By _paypal_password_field = By.id("password");
	private By _paypal_login_button   = By.id("btnLogin");
	private By _paypal_continue_button= By.xpath("//*[@id=\"button\"]/button");
	private By _paypal_continue_button2= By.id("fiSubmitButton");
	private By _paypal_stayeLoggedIn  = By.id("keepMeLogin");
	private By _paypal_agreeContinuebutton = By.id("confirmButtonTop"); ////*[@id="confirmButtonTop"]
	private By _paypal_agreeContinuebutton2 = By.id("consentButton"); ////*[@id="confirmButtonTop"]

	//region PayPal Login Methods
	public void enterPayPalEmail(String PAYPAL_EMAIL)
	{
		driver.findElement(_paypal_email_field).sendKeys(PAYPAL_EMAIL);
		log.info("Entered PayPal Email");
	}

	public void clickPayPalNextButton()
	{
		driver.findElement(_paypal_next_buttom).click();
		log.info("Clicked Next");
	}

	public void enterPayPalPassword(String paypalPassword)
	{
		driver.findElement(_paypal_password_field).sendKeys(paypalPassword);
		log.info("Entered PayPal Password");
	}

//	public void unCheckStayLoggedIn() {
//		WebElement stayeLoggedInElement = driver.findElement(paypal_stayeLoggedIn);
//
//		if (stayeLoggedInElement.isSelected() ){
//			stayeLoggedInElement.click();
//		}
//		//driver.findElement(paypal_stayeLoggedIn).click();
//	}

	public void clickPayPalLoginButton()
	{
		driver.findElement(_paypal_login_button).click();
		log.info("Clicked PayPal Login");
	}
	//endregion

	//region Complete PayPal Transaction Methods
	public void clickPayPalContinueButton()
	{
		driver.findElement(_paypal_continue_button).click();
		log.info("Clicked PayPal Continue");
	}

	public void clickPayPalContinueButton2() //paypal made some changes
	{
		driver.findElement(_paypal_continue_button2).click();
		log.info("Clicked PayPal Continue2");
	}

	public void clickPaypalAgreeAndContinuebutton()
	{
		driver.findElement(_paypal_agreeContinuebutton).click();
		log.info("Clicked PayPal Agree and Continue");
	}

	public void clickPaypalAgreeAndContinuebutton2() //paypal made some changes
	{
		driver.findElement(_paypal_agreeContinuebutton2).click();
		log.info("Clicked PayPal Agree and Continue2");
	}
	//endregion

	public void completePayPalSiteCheckout() {
		enterPayPalEmail(StaticData.PAYPAL_EMAIL);
		clickPayPalNextButton();
		enterPayPalPassword(StaticData.PAYPAL_PASSWORD);
		clickPayPalLoginButton();
		clickPaypalAgreeAndContinuebutton();
	}
}