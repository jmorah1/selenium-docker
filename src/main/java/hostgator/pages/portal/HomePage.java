package hostgator.pages.portal;

import hostgator.driver.TestDriver2;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends TestDriver2 {

    private static Logger log = LogManager.getLogger(HomePage.class.getName());

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
    }

    private void NoInterceptClick(WebElement elementToClick){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", elementToClick);
    }
    //region Portal Home Page Elements
    private By _billingNav    = By.linkText("Billing");
    private By _makePayment   = By.linkText("Make a Payment");
    private By _nextButton    = By.id("loginBtn");
    private By _passwordField = By.id("password");
    private By _loginButton   = By.id("loginBtn");
    private By _hosting       = By.xpath("//*[@id=\"gbclient\"]/div[1]/ul/li[2]/a");
    private By _billingHistory = By.linkText("Billing History");
    //endregion

    //region Billing Tab Navigation Methods
    private void clickBillingNav()
    {
        driver.findElement(_billingNav).click();
        log.info("Clicked Billing Nav");
    }

    private void clickMakePayment()
    {
        driver.findElement(_makePayment).click();
        log.info("Clicked Make a Payment");
    }

    private void clickBillingHistory() {
        driver.findElement(_billingHistory).click();
        log.info("Clicked Billing History");
    }
    //endregion

    public void clickHosting()
    {
        WebElement element = driver.findElement(_hosting);

        JavascriptExecutor executor = (JavascriptExecutor)driver;

        executor.executeScript("arguments[0].click();", element);
        log.info("Clicked Hosting Nav");
    }

    public void navigateToBillingHistory() {
        clickBillingNav();
        clickBillingHistory();
    }

    public void navigateToMakePayment() {
        clickBillingNav();
        clickBillingHistory();
    }
}