package com.extentReportTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass extends BaseTest {

    @Test(groups = {"smoke"})
    public void validateLogo() {
    	driver.get("https://opensource-demo.orangehrmlive.com/");
    	extentTest.info("Navigated to the Url");
    	boolean logoStatus = driver.findElement(By.xpath("//div[@class='orangehrm-login-branding']")).isDisplayed();
    	Assert.assertTrue(logoStatus, "Logo not dispayed");
    }
    
     @Test(groups = {"smoke","sanity"})
     public void validateValidLogin() {
     	driver.get("https://opensource-demo.orangehrmlive.com/");
    	extentTest.info("Navigated to the Url");
    	
        driver.findElement(By.name("username")).sendKeys("Admin");
    	extentTest.info("Entered the username");

        driver.findElement(By.name("password")).sendKeys("admin123");
    	extentTest.info("Entered the password");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    	extentTest.info("Performed the Login Operation");

        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, "OrangeHRM");
     }
    
     @Test(groups = {"sanity","Functional"})
     public void validateJobTitle() {
      	driver.get("https://opensource-demo.orangehrmlive.com/");
    	extentTest.info("Navigated to the Url");

        driver.findElement(By.name("username")).sendKeys("Admin");
    	extentTest.info("Entered the username");

        driver.findElement(By.name("password")).sendKeys("admin123");
    	extentTest.info("Entered the password");

        driver.findElement(By.xpath("//button[@type='submit']")).click();
     	extentTest.info("Performed the Login Operation");
     	
        driver.findElement(By.xpath("//span[text()='Admin']/..")).click();
        driver.findElement(By.xpath("//span[text()='Job ']")).click();
        driver.findElement(By.xpath("//a[text()='Job Titles']")).click();
        extentTest.info("Selected Job title from dropdown");
        
        boolean jobTitle = driver.findElement(By.xpath("//h6[text()='Job Titles']")).isDisplayed();
        Assert.assertTrue(false,"Job Title not displayed");
     }
     
	
}
