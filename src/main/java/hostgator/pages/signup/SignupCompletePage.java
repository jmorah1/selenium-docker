package hostgator.pages.signup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupCompletePage {

	private static Logger log = LogManager.getLogger(SignupCompletePage.class.getName());
	private WebDriver driver;
	public SignupCompletePage(WebDriver  driver)
	{
		this.driver=driver;
	}
	
	private By _paymentCompleteSection = By.xpath("//*[@id=\"welcome-section\"]");

	public void verifypaymentComplete()
	{
		driver.findElement(_paymentCompleteSection).isDisplayed();
		log.info("Verified Payment Complete URL");
	}
	
}
