package kubernetes;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGridWithKubernetes {

	public WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void setUp(String browser) throws MalformedURLException {
		
		String girdURL="http://127.0.0.1:55767/";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if(browser.equalsIgnoreCase("chrome")) {	
			
			capabilities.setBrowserName(browser);	
			System.out.println("#### Test Case Exceution Start on -> "+browser);
		}
		
		else if(browser.equalsIgnoreCase("firefox")) {
			
			capabilities.setBrowserName(browser);
			System.out.println("#### Test Case Exceution Start on -> "+browser);
		}
		else if(browser.equalsIgnoreCase("MicrosoftEdge")) {
			
			capabilities.setBrowserName(browser);
			System.out.println("#### Test Case Exceution Start on -> "+browser);

		}
		
		driver=new RemoteWebDriver(new URL(girdURL), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	}
	
	@Test
	public void googleTestOnChrome() {
		
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']/center/input[2]")).click();
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Google Doodles","Title Not Match");
	}
	

	@Test
	public void OrangeHRMTestOnFirefox() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String actualTitle = driver.getTitle();
		String ExpectedTitle="OrangeHRM";
		Assert.assertEquals(actualTitle, ExpectedTitle,"Title Not Match");
	}
	@Test
	public void TestOnMicrosoftEdge() {
		
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.findElement(By.xpath("//input[contains(@class,'search-input')]")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[contains(@class,'search-button')]")).click();
		driver.findElement(By.xpath("(//div[contains(@id,'search-result-link')])[1]")).click();
		
		String parentHandle = driver.getWindowHandle();
		Set<String> allHandle = driver.getWindowHandles();
		
		for(String handle:allHandle) {
			
			if(!handle.equals(parentHandle)) {
				
				driver.switchTo().window(handle);
				String actualTitle = driver.getTitle();
				Assert.assertEquals(actualTitle, "Selenium - Wikipedia","title mismatch");
			}
			
		}
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("#### Test Case Exceution End ######");
		driver.quit();
	}
}
