package copytext;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ass1Flipkart {

	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.get("https://klarity-develop.demandscience-apps.com/");
		driver.findElement(By.id("input_email")).sendKeys("aparna@appliedaiconsulting.com");
		driver.findElement(By.id("input_password")).sendKeys("Asdfghjkl123");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//button[text()='Accept All']")).click();
		driver.findElement(By.xpath("//a[@id='action_menu_search']//button//*[name()='svg']")).click();
		
		driver.findElement(By.xpath("//div[text()='Company name']")).click();
		
		 driver.findElement(By.xpath("//input[@placeholder='Enter company name or url']")).sendKeys("tata");
		 Thread.sleep(3000);
		 WebElement target = driver.findElement(By.xpath("(//div[contains(text(),'All containing')])[1]"));
		 target.click();
		 driver.findElement(By.xpath("(//button[contains(@class, 'grid')])[2]")).click();
		 driver.findElement(By.xpath("(//button[contains(@class, 'p-[9px]')])[3]")).click();
		 driver.findElement(By.xpath("//button[text()='Dismiss']")).click();
		 driver.findElement(By.xpath("(//p[@data-testid='paragraph'])[1]")).click();
		 driver.findElement(By.xpath("//div[@class='flex flex-row justify-between w-full']/descendant::*[name()='svg']")).click();
		 driver.findElement(By.xpath("//div[text()='Lists']")).click();
		
		 WebElement input = driver.findElement(By.xpath("//input[@placeholder='Find a list']"));
	        input.sendKeys(Keys.SHIFT, Keys.INSERT);
	}
}
