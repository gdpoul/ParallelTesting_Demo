package seleniumgridPractice;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Grid1 {
	
	public WebDriver driver;
	DesiredCapabilities capabilities=new DesiredCapabilities();
	String hub_URL="http://localhost:4444";
	
	@Test
	public void test1_OnChrome() throws MalformedURLException {
		
		capabilities.setBrowserName("chrome");
		capabilities.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(hub_URL), capabilities);
		
		System.out.println("chrome browser launch successfully");
		// test the google logo
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.google.com/");
		boolean logoStatus = driver.findElement(By.xpath("//img[@class='lnXdpd']")).isDisplayed();
		Assert.assertEquals(logoStatus, true,"Logo not dispalyed");
		System.out.println("chrome test passed");
		driver.quit();
	}
	
	@Test
	public void test2_OnFirefox() throws MalformedURLException {
		capabilities.setBrowserName("firefox");
		capabilities.setPlatform(Platform.WIN10);
		driver=new RemoteWebDriver(new URL(hub_URL), capabilities);
		
		System.out.println("firefox browser launch successfully");
		//test fb title
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.facebook.com/");
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, "Facebook – log in or sign up","Title not match");
		System.out.println("firefox test passed");
		driver.quit();
	}

}
