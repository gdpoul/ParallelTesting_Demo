package pdfReader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadPdfInDifferentBrowser {
	
	WebDriver driver;
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		driver.get("hdfcbank.com/personal/resoureces/rates");	
	}
	
	@Test
	public void test() throws Exception {
		
		driver.findElement(By.id("ecSubmitAgreeButton")).click();
		driver.findElement(By.linkText("trillions of PDFs")).click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		
		URL pdfUrl=new URL(url);
		URLConnection urlConnection=pdfUrl.openConnection();
		urlConnection.addRequestProperty("User-Agent", "Ganesh");
		InputStream ip = urlConnection.getInputStream();		
		BufferedInputStream bf = new BufferedInputStream(ip);
		
		// validate pdf
		PDDocument doc = PDDocument.load(bf);	
		int totalPage = doc.getNumberOfPages();

	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	

}
