package hostgator;

import hostgator.driver.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class DockerTests extends BaseTest {
	private static Logger log = LogManager.getLogger(DockerTests.class.getName());

	@Test
	public void Test1() throws InterruptedException {
		driver.get("http://www.google.com");
		System.out.println("Title of page is "+driver.getTitle());
		System.out.println("URL of page is "+driver.getCurrentUrl());
		Thread.sleep(10000);
		log.info("Got to Test1");
	}

	@Test
	public void Test2() throws InterruptedException{
		driver.get("http://www.netflix.com/");
		log.info("Title of page is "+driver.getCurrentUrl());
		log.info("URL of page is "+driver.getTitle());
		System.out.println("Page Title 2 is " + driver.getTitle());
		Thread.sleep(10000);
		log.info("Got to Test2");
	}

	@Test
	public void Test3() throws InterruptedException{
		driver.get("http://www.apple.com/");
		log.info("Title of page is "+driver.getCurrentUrl());
		log.info("URL of page is "+driver.getTitle());
		System.out.println("Page Title 2 is " + driver.getTitle());
		Thread.sleep(10000);
		log.info("Got to Test2");
	}
}
