package dockerSeleniumGrid;

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

public class Docker1 {

	@Test
	public void test1() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		cap.setVersion("107.0");
		cap.setPlatform(Platform.LINUX);
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.4:4445"), cap);
		
		System.out.println("#### TEST CASE EXECUTION START ####");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String actualTitle = driver.getTitle();
		String ExpectedTitle="OrangeHRM";
		Assert.assertEquals(actualTitle, ExpectedTitle,"Title Not Match");
		driver.quit();
	}

}
