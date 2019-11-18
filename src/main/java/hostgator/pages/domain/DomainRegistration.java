package hostgator.pages.domain;

import hostgator.driver.TestDriver2;
import hostgator.util.StaticData;
import org.apache.logging.log4j.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class DomainRegistration extends TestDriver2 {

    private static Logger log = LogManager.getLogger(DomainRegistration.class.getName());
    Random random = new Random();

    public DomainRegistration(WebDriver driver)
    {
        this.driver=driver;
    }

    private By _domainfield   = By.name("search_contents");
    private By _searchDomainButton = By.xpath("//*[@id=\"spa_content\"]/div/div[1]/div/section/div/div[1]/button");
    private By _continueCheckoutButton = By.xpath("//*[@id=\"cart_brick\"]/div/div[2]/form/button");


    public void enterDomain()
    {
        driver.findElement(_domainfield).sendKeys("hgtest"+random.nextInt(10000)+"domain"+random.nextInt(10000)+".com");
        log.info("Entered Domain");
    }

    public void clickDomainSearchButton()
    {
        driver.findElement(_searchDomainButton).click();
        log.info("Clicked Search Domain");

    }

    public void clickContinueCheckoutButton(){
//        driver.findElement(continueCheckoutButton).click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(_continueCheckoutButton)).click();
        log.info("Clicked Continue Checkout");
    }

    public void enterDomainAndSearch(){
        String searchDomain = StaticData.DOMAIN_NAME+random.nextInt(10000)+"domain"+random.nextInt(10000)+".com";

        driver.findElement(_domainfield).sendKeys(searchDomain);
        log.info("Entered Domain");

        clickDomainSearchButton();

        @SuppressWarnings("deprecation")
        WebDriverWait w = new WebDriverWait(driver, 10);
        w.until(ExpectedConditions.urlContains(searchDomain));
    }

}