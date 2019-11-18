package hostgator.pages.portal;

import hostgator.driver.TestDriver2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Hosting extends TestDriver2 {
    private static Logger log = LogManager.getLogger(HomePage.class.getName());

    public Hosting(WebDriver driver)
    {
        this.driver=driver;
    }

    By billingNav    = By.linkText("Add a Package");
    By shared = By.cssSelector("div[data-value='/signup/shared/3/36/SHARED3660']");


    public void addPackage()
    {
        WebElement element = driver.findElement(billingNav);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        log.info("Clicked Add a Package");
    }

    public void shared()
    {
        WebElement element = driver.findElement(shared);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        log.info("Clicked shared");
    }

}
