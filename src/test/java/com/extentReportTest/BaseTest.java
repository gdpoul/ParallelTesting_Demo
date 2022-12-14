package com.extentReportTest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentTest extentTest;

	@BeforeSuite
	public void generateExtentreport() {
		extentReports = new ExtentReports();
		sparkReporter = new ExtentSparkReporter("AllTest.html");
		extentReports.attachReporter(sparkReporter);
	}
	
	@AfterSuite
	public void generateReport() throws IOException {
		extentReports.flush();
		Desktop.getDesktop().browse(new File("AllTest.html").toURI());
	}

	@BeforeTest()
	public void setUp(ITestContext context) {
		String browser=context.getCurrentXmlTest().getParameter("browser");
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        Capabilities capabilities=((RemoteWebDriver)driver).getCapabilities();
        String device = capabilities.getBrowserName() +" "+
        capabilities.getBrowserVersion().substring(0, capabilities.getBrowserVersion().indexOf("."));
        String author=context.getCurrentXmlTest().getParameter("author");
        
		extentTest = extentReports.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}
	
	@AfterMethod
	public void checkStatus(Method m, ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			String screenshotPath=null;
			screenshotPath = captureScreenShot(result.getTestContext().getName()
					+"_"+result.getMethod().getMethodName()+".jpg");
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(result.getThrowable());
		} 
		else if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.pass(m.getName()+" is passed");
		}
		
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	public String captureScreenShot(String file) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots/" + file + "");
		Files.copy(src, dest);
		String path = dest.getAbsolutePath();
		return path;
	}

}
