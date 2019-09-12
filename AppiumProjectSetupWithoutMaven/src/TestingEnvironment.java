import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class TestingEnvironment {

	protected AndroidDriver<AndroidElement> driver = null;

	DesiredCapabilities dc = new DesiredCapabilities();

	public static void main(String[] args) throws MalformedURLException {

		System.out.println("Hello World!");

		TestingEnvironment obj_created = new TestingEnvironment();

		obj_created.setUp();

		obj_created.testUntitled();

		obj_created.tearDown();

	}

	// @BeforeMethod
	public void setUp() throws MalformedURLException {

		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("playformName", "android");

		// dc.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
		driver.setLogLevel(Level.INFO);
	}

	// @Test
	public void testUntitled() {
		driver.getKeyboard().sendKeys("company");

		driver.findElement(By.id("com.experitest.ExperiBank:id/passwordTextField")).sendKeys("company");
		
		new WebDriverWait(driver, 30)
		.until(ExpectedConditions.presenceOfElementLocated(By.id("com.experitest.ExperiBank:id/loginButton")));

		driver.findElement(By.id("com.experitest.ExperiBank:id/loginButton")).click();

		new WebDriverWait(driver, 30)
				.until(ExpectedConditions.presenceOfElementLocated(By.id("com.experitest.ExperiBank:id/logoutButton")));

		driver.findElement(By.id("com.experitest.ExperiBank:id/logoutButton")).click();

	}

	/*
	 * driver.findElement(By.xpath("//*[@id='passwordTextField']")).click(); new
	 * WebDriverWait(driver,
	 * 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
	 * "//*[@id='passwordTextField']")));
	 * driver.findElement(By.xpath("//*[@id='passwordTextField']")).click();
	 * driver.findElement(By.xpath("//*[@id='passwordTextField']")).click();
	 * 
	 * new WebDriverWait(driver,
	 * 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
	 * "//*[@id='passwordTextField']")));
	 * driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys(
	 * "cocompany"); new WebDriverWait(driver,
	 * 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
	 * "//*[@text='Login']")));
	 * driver.findElement(By.xpath("//*[@text='Login']")).click(); new
	 * WebDriverWait(driver,
	 * 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
	 * "//*[@text='Logout']")));
	 * driver.findElement(By.xpath("//*[@text='Logout']")).click();
	 */

	// @AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
