package hostgator;

import hostgator.driver.BaseTest;
import hostgator.driver.TestDriver;
import hostgator.driver.TestDriver2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public class MadeToPass extends BaseTest {
	private static Logger log = LogManager.getLogger(MadeToPass.class.getName());

	@Test
	public void PassingTest1() throws InterruptedException{
		driver.get("http://www.google.com");
		log.info("Got to PassingTest1");
		System.out.println("Page Title 1 is " + driver.getTitle());
		Thread.sleep(10000);
		Assert.assertTrue(true);
	}

//	@Test
//	public void PassingTest2() throws InterruptedException{
//		driver.get("http://www.netflix.com/");
//		log.info("Got to PassingTest2");
//		System.out.println("Page Title 2 is " + driver.getTitle());
//		Thread.sleep(10000);
//		Assert.assertTrue(true);
//	}
//	@Test
//	public void PassingTest3() throws InterruptedException{
//		log.info("Got to PassingTest3");
//		System.out.println("Page Title 3 is " + driver.getTitle());
//		Thread.sleep(10000);
//		Assert.assertTrue(true);
//	}
//
//	@Test
//	public void PassingTest4() throws InterruptedException{
//		driver.get("http://www.netflix.com/");
//		log.info("Got to PassingTest4");
//		System.out.println("Page Title 4 is " + driver.getTitle());
//		Thread.sleep(10000);
//		Assert.assertTrue(true);
//	}
//
//	@Test
//	public void PassingTest5() throws InterruptedException{
//		driver.get("http://www.apple.com/");
//		log.info("Got to PassingTest5");
//		System.out.println("Page Title 5 is " + driver.getTitle());
//		Thread.sleep(10000);
//		Assert.assertTrue(true);
//	}

	@AfterTest
	public void teardown() {
		driver.quit();
		log.info("Closing Driver");
	}
}
