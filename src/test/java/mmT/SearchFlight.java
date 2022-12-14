package mmT;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class SearchFlight {
	WebDriver driver;
	@Test
	public void login()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.makemytrip.com/");
		driver.switchTo().frame(2);
		driver.findElement(By.id("webklipper-publisher-widget-container-notification-close-div")).click();
				
	}
	@Test
	public void selectFlight()
	{
		Actions act = new Actions(driver);
	
		
		driver.findElement(By.xpath("//ul[@class='makeFlex font12']/descendant::span[text()='Flights']")).click();
	}

}
