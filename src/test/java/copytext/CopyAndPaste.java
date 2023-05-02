package copytext;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CopyAndPaste {
	WebDriver driver;
	
	
	@BeforeTest
	public void setUp() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

		driver.get("https://klarity-staging.demandscience-apps.com/");
		
		// login
		driver.findElement(By.id("input_email")).sendKeys("aparna@appliedaiconsulting.com");
		driver.findElement(By.id("input_password")).sendKeys("Asdfghjkl123");
		driver.findElement(By.id("action_signin")).click();
		driver.findElement(By.xpath("//div[text()='Search']")).click();
		driver.findElement(By.xpath("//div[text()='Company name']")).click();
		driver.findElement(By.id("headlessui-combobox-input-:r1f:")).sendKeys("tata");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='flex-1 min-w-0'])[4]")).click();
//		driver.findElement(By.xpath("(//div[contains(@class,'flex flex-row items-center bg-white ')]/div)[1]")).click();
//		driver.findElement(By.xpath("(//*[name()='svg'])[53]")).click();
//		driver.findElement(By.xpath("//button[text()='Reveal ']")).click();
//		Thread.sleep(4000);
		driver.findElement(By.xpath("(//div[contains(@class,'flex flex-row items-center bg-white ')])[1]")).click();
		Thread.sleep(4000);
	}
	
	@Test
	public void validateCopyNumber() throws Exception {
		String expectedNumber = driver.findElement(By.xpath("(//div[@class='relative'])[39]")).getText();
		driver.findElement(By.xpath("//div[@class='flex flex-row items-center px-6 py-3']"
				+ "//div[@class='flex-1 min-w-0']//div[2]//*[name()='svg']")).click();
		driver.findElement(By.xpath("//div[text()='Lists']")).click();
		
		WebElement findList = driver.findElement(By.id("headlessui-combobox-input-:rf0:"));
		findList.click();
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		
		String copiedNumber = findList.getText();
		Assert.assertEquals(copiedNumber, expectedNumber,"copied wrong string");
		
	}
	
	@AfterTest
	public void TearDown() {
		
	}

}
