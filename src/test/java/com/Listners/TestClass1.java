package com.Listners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ITestListnersClass.class)
public class TestClass1 {
	
	@Test
	public void testMethod1() {
		System.out.println("I am inside test method 1");
	}
	
	@Test
	public void testMethod2() {
		System.out.println("I am inside test method 2");
		Assert.fail();
	}
	
	@Test(timeOut = 1000)
	public void testMethod3() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("I am inside test method 3");
		
	}
	
	@Test(dependsOnMethods ="testMethod3" )
	public void testMethod4() {		
		System.out.println("I am inside test method 4");
	}




}
