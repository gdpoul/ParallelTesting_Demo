package pdfReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PDFReaderTest {
	
	WebDriver driver;
	String url="file:///C:/Users/ganes/Downloads/invoice.pdf";
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
		
	}
	
	@Test
	public void pdfReaderTest() throws IOException {
		
		URL pdfUrl=new URL(url);
		InputStream ip = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(ip);
		PDDocument doc = PDDocument.load(bf);
		
		// pdf total pages
		int totalPage = doc.getNumberOfPages();
		System.out.println("pdf pages: "+totalPage);
		Assert.assertEquals(totalPage, 1);
		
		System.out.println("=============pdf content==========");
		
		//page content
		PDFTextStripper pdfStripper=new PDFTextStripper();
		String pdfText = pdfStripper.getText(doc);
		System.out.println(pdfText);
		Assert.assertTrue(pdfText.contains("Nivoda USA LLC"));
		Assert.assertTrue(pdfText.contains("Invoice No: SU-276840"));
		Assert.assertTrue(pdfText.contains("Email : orders@nivoda.net"));
	}
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
