package hostgator.pages.domain;

import hostgator.util.StaticData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CartCheckout {

    private static Logger log = LogManager.getLogger(CartCheckout.class.getName());
    Random random = new Random();


    private WebDriver driver;
    public CartCheckout(WebDriver driver)
    {
        this.driver=driver;
    }

    //region Your Account Elements
    private By _emailField           = By.name("email");
    private By _passwordField        = By.name("password");
    private By _confirmPasswordfield = By.name("confirm_password");
    private By _continueButton       = By.xpath("//*[@id=\"checkout\"]/div[2]/div[1]/div/section/div/div[2]/div[1]/section[1]/div[6]/button");
    private By _pinField             = By.name("pin");
    //endregion

    //region Billing Info Elements
    private By _firstNameField = By.name("first_name");
    private By _lastNameField  = By.name("last_name");
    private By _homePhoneField = By.name("home_phone");
    private By _addressField   = By.name("address");
    private By _cityField      = By.name("city");
    private By _zipCodeField   = By.name("postal_code");
    //endregion

    //region Payment Type Elements
    private By _creditCardradio      = By.name("//");
    private By _payPalradio          = By.name("//");
    private By _nameOnCardField      = By.name("name_on_card");
    private By _ccNumerField         = By.name("number");
    private By _expDateMonthDropDown = By.name("//");
    private By _expDateYearDropDown  = By.name("//");
    private By _cvvCodeField         = By.name("cvv");
 //endregion

    //region Order Summary Elements
    private By _continueToCheckout   = By.linkText("Continue to Checkout");
    private By _tos                  = By.xpath("//*[@id=\"checkout\"]/div[2]/div[3]/div/section/div/div/div[2]/div/div/p[2]/div/ins"); //By.id("accept-tos")
    private By _placeOrderButton = By.xpath("//*[@id=\"checkout\"]/div[2]/div[3]/div/section/div/div/div[4]/ul/li[2]/button");
    //endregion

    //region Your Account Methods
    public void enterEmail()
    {
        driver.findElement(_emailField).sendKeys(StaticData.DOMAIN_NAME +random.nextInt(10000)+"domain" +random.nextInt(100000)+ "@endurance.com");
        log.info("Entered Email");
    }

    public void enterPassword(String password)
    {
        driver.findElement(_passwordField).sendKeys(password);
        log.info("Entered Password");
    }

    public void enterConfirmPassword(String password)
    {
        driver.findElement(_confirmPasswordfield).sendKeys(password);
        log.info("Entered Confirm Password");
    }

    public void clickContinue(){
        driver.findElement(_continueButton).click();
    }

    public void enterPin(String pin){
        driver.findElement(_pinField).sendKeys(pin);
    }
    //endregion

    //region Billing Info Methods
    public void enterFirstName(String firstName) {
        driver.findElement(_firstNameField).sendKeys(firstName);
        log.info("Entered First Name");
    }

    public void enterlastName(String lastName) {
        driver.findElement(_lastNameField).sendKeys(lastName);
        log.info("Entered Last name");
    }

    public void enterHomePhone(String homePhone) {
        driver.findElement(_homePhoneField).sendKeys(homePhone);
        log.info("Entered Phone");
    }

    public void enterAddress(String address) {
        driver.findElement(_addressField).sendKeys(address);
        log.info("Entered Address");
    }

    public void enterCity(String city) {
        driver.findElement(_cityField).sendKeys(city);
        log.info("Entered City");
    }

    public void enterZipCode(String zip) {
        driver.findElement(_zipCodeField).sendKeys(zip);
        log.info("Entered Zip");
    }
    //endregion

    //region Payment Type Methods
    public void enterCCName(String ccName) {
        driver.findElement(_nameOnCardField).sendKeys(ccName);
        log.info("Entered Zip");
    }

    public void enterCCNumber(String cc) {
        driver.findElement(_ccNumerField).sendKeys(cc);
        log.info("Entered CC #");
    }

    public void enterCVV(String cvv) {
        driver.findElement(_cvvCodeField).sendKeys(cvv);
        log.info("Entered CVV");
    }
    //endregion

    //region Review Order Details Methods
    public void clickContinueToCheckout() {
        driver.findElement(_continueToCheckout).click();
    }

    public void acceptTOS() {
//        driver.findElement(tos).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(_tos)).click();

    }

    public void clickPlaceOrder() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(_placeOrderButton)).click();
    }
    //endregion

    public void enterPasswordAndConfirm(String password)
    {
        enterPassword(password);
        enterConfirmPassword(password);
    }

    public void enterBillingInfo()
    {
        enterFirstName(StaticData.FIRST_NAME);
        enterlastName(StaticData.LAST_NAME);
        enterHomePhone(StaticData.PHONE);
        enterAddress(StaticData.ADDRESS1);
        enterCity(StaticData.CITY);
        enterZipCode(StaticData.ZIP);
    }

    public void enterCCInfo()
    {
        enterCCName(StaticData.TEST_CREDIT_CARD_NAME);
        enterCCNumber(StaticData.TEST_CREDIT_CARD_NUMBER);
        enterCVV(StaticData.TEST_CREDIT_CARD_CVV);
    }

    public void acceptTOSAndPlaceOrder()
    {
        acceptTOS();
        clickPlaceOrder();
    }

}