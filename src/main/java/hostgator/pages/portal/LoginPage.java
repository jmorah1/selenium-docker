package hostgator.pages.portal;

import hostgator.driver.TestDriver2;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends TestDriver2 {

    private static Logger log = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage(WebDriver driver)
    {
        this.driver=driver;
    }

    //region Portal Login Page Elements
    private By _emailField    = By.id("email");
    private By _nextButton    = By.id("loginBtn");
    private By _passwordField = By.id("password");
    private By _loginButton   = By.id("loginBtn");
    //endregion

    //region Portal Login Page Methods
    public void enterExistingAccountEmail(String email)
    {
        driver.findElement(_emailField).sendKeys(email);
        log.info("Entered Existing Email");
    }

    public void clickNextButton()
    {
        driver.findElement(_nextButton).click();
        log.info("Clicked Next");
    }

    public void enterExistingAccountPassword(String password)
    {
        driver.findElement(_passwordField).sendKeys(password);
        log.info("Entered Password");
    }

    public void clickLoginButton()
    {
        driver.findElement(_loginButton).click();
        log.info("Clicked Login");
    }
    //endregion

    public void portalLogin(String email, String password)
    {
        enterExistingAccountEmail(email);
        clickNextButton();
        enterExistingAccountPassword(password);
        clickLoginButton();

        @SuppressWarnings("deprecation")
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("home-container")));
    }

}