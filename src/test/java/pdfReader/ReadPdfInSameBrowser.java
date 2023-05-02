package pdfReader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadPdfInSameBrowser {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(); 
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();		
		driver.get("https://www.inkit.com/blog/pdf-the-best-digital-document-management");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
		System.out.println("pdf pages: "+totalPage);
		Assert.assertEquals(totalPage, 43);
		
		System.out.println("==============pdf meta data==============");
        System.out.println(doc.getVersion());
        System.out.println(doc.isEncrypted());
        System.out.println(doc.getDocumentId());
        
        System.out.println(doc.getCurrentAccessPermission().canPrint());
        System.out.println(doc.getCurrentAccessPermission().isReadOnly());
        
        System.out.println(doc.getDocumentInformation().getSubject());
        System.out.println(doc.getDocumentInformation().getTitle());
        System.out.println(doc.getDocumentInformation().getCreator());
        
        System.out.println("====================pdf full content=======================");
        // read full pdf content
        PDFTextStripper pdfStripper=new PDFTextStripper();
        pdfStripper.setStartPage(42);
        String pdfFullText = pdfStripper.getText(doc);
        System.out.println(pdfFullText);
        Assert.assertTrue(pdfFullText.contains("Duff Johnson"));
        
        doc.close();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
