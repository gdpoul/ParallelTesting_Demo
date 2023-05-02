package seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

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

public class GridTest {

	public WebDriver driver;
	
	@Parameters("Browser")
	@BeforeTest
	public void setUp(String browserName) throws MalformedURLException {
		
		DesiredCapabilities cap=new DesiredCapabilities();
		String hubURL="http://localhost:4444";
		
		if(browserName.equals("chrome")) {
			
			cap.setBrowserName(browserName);
			cap.setPlatform(Platform.WIN10);
			System.out.println("#### Test Case Exceution Start on -> "+browserName);

		}
		else if(browserName.equals("firefox")) {
			cap.setBrowserName(browserName);
			cap.setPlatform(Platform.WIN10);
			System.out.println("#### Test Case Exceution Start on -> "+browserName);

		}
		else if (browserName.equals("MicrosoftEdge")) {
			cap.setBrowserName(browserName);
			cap.setPlatform(Platform.WIN10);
			System.out.println("#### Test Case Exceution Start on -> "+browserName);


		}
		System.out.println("Hello1");
		driver=new RemoteWebDriver(new URL("http://192.168.1.67:4444"), cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println("Hello2");		
		
	}
	@Test
	public void testOrangeHRMOnChrome() {
	
		  driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		  driver.findElement(By.name("username")).sendKeys("Admin");
		  driver.findElement(By.name("password")).sendKeys("admin123");
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
		  
		  String actualTitle = driver.getTitle();	  
		  Assert.assertEquals(actualTitle, "OrangeHRM","Title Not match");
	}
	
	@Test
	public void testFacebookOnFirefox() {
		driver.get("https://www.facebook.com/");
		String actualTitle = driver.getTitle();
		String ExpectedTitle="Facebook – log in or sign up";
		Assert.assertEquals(actualTitle, ExpectedTitle,"Title Not Match");	

	}
	
	@Test
	public void testGoogleOnEdge() {
		driver.get("https://www.google.com/");
		driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']/center/input[2]")).click();
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, "Google Doodles","Title Not Match");

	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}


}
