package edu.gatech.noise.bismark;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BismarkMain {

	public static void main (String args[])
	{
		//System.setProperty("", "c://workspace/bismarkautotest/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\workspace\\bismarkautotest\\chromedriver.exe");
		System.out.println("Bismark Automated Test v0.1");
		
		String browser="Chrome";
		
		WebDriver d = null;
	
		if(browser.equals("Firefox"))
			d = new FirefoxDriver();
		else if(browser.equals("Chrome"))
			d = new ChromeDriver();
		
		d.get("http://192.168.142.1");
		
		try {
			System.out.print("-- Step 1. Checking splash screen: "); 
			WebElement we =  d.findElement(By.partialLinkText("Remind Me Later"));
			we.click();
			System.out.println("passed");
		}
		catch(org.openqa.selenium.NoSuchElementException nse) 
		{
			System.out.println("no splash screen detected. Skipping..");
		}
		
		try {
			System.out.print("-- Step 2. Configuring: "); 
			WebElement we =  d.findElement(By.partialLinkText("Configure"));
			we.click();
			System.out.println("passed");
		}
		catch(org.openqa.selenium.NoSuchElementException nse) 
		{
			System.out.println("No Configure token detected. Exiting...");
			
			d.close();
			d.quit();
		}
		
		try {
			System.out.print("-- Step 3. Logging in: ");
			WebElement we;
			we =  d.findElement(By.name("username"));
			String username=we.getAttribute("value");
			if(!username.equals("root")) {
				we.clear();
				we.sendKeys("root");
			}
			d.findElement(By.name("password")).sendKeys("XXXXXX");
			d.findElement(By.xpath("//input[@value='Login']")).click();
			
			System.out.println("passed");
		}
		catch(org.openqa.selenium.NoSuchElementException nse) 
		{
			System.out.println(" No token detected ");
			nse.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println(" Exiting.. ");
			e.printStackTrace();
			d.close();
			d.quit();
		}
		
		try {
			System.out.print("-- Step 4. Image Flashing: "); 
			WebElement we =  d.findElement(By.partialLinkText("System"));
			we.click();
			System.out.println("passed");
		}
		catch(org.openqa.selenium.NoSuchElementException nse) 
		{
			System.out.println(" Exiting...");
			
			d.close();
			d.quit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			d.close();
			d.quit();
		}

		//d.close();
		//d.quit();
		
	}

	
}
