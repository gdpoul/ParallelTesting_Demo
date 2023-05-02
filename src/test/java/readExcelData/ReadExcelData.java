package readExcelData;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilpackage.FileLib;

public class ReadExcelData {

	static String EXCELPATH = "./resources/testdata.xlsx";
	static String SHEETNAME = "invalidCreds";

	public static void main(String[] args) throws EncryptedDocumentException, 
	             IOException, InterruptedException {

		// test OrangeHrm login page with invalid credential

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://opensource-demo.orangehrmlive.com/");

		int rc = FileLib.getRowCount(EXCELPATH,SHEETNAME);

		for (int i = 1; i <= rc; i++) {
			String userName =FileLib.readExcelData(EXCELPATH,SHEETNAME,i, 0);
			String passWord = FileLib.readExcelData(EXCELPATH,SHEETNAME,i, 1);
			
			WebElement usn = driver.findElement(By.name("username"));
			usn.sendKeys(userName);
			WebElement pwd = driver.findElement(By.name("password"));
			pwd.sendKeys(passWord);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

			Thread.sleep(3000);
			
//			usn.clear();
//			pwd.clear();
			
		}
	}


}
