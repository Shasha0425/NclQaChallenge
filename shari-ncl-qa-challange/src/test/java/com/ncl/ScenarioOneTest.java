package com.ncl;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScenarioOneTest {

	@Test
	public void portDepartureTest() throws Exception {

		System.out.println("Starting ScenarioOneTest");

// 		Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		maximize browser window
		driver.manage().window().maximize();

//		open ncl.com
		String url = "https://www.ncl.com/";
		driver.get(url);
		System.out.println("Page is opened");

		// GIVEN a Guest
		WebElement logInButton = driver.findElement(By.linkText("LOG IN"));
		Assert.assertTrue(logInButton.isDisplayed(), "Log In Button is Not Visble");

		// AND I am on Homepage
		String expectedUrl = "https://www.ncl.com/";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual Url is not the same as expected.");

		// AND I navigated to “Ports” page
		WebElement exploreButton = driver.findElement(By.xpath(
				"/html//header[@id='header']/section[@class='header_desktop']/div[@class='header_desktop_wrapper']/nav[@class='header_navigation']/div//div[@class='container']/div/div[1]/div/ul//a[@title='Explore']"));
		exploreButton.click();
		Thread.sleep(2000);
		WebElement portButton = driver.findElement(By.xpath(
				"/html//header[@id='header']/section[@class='header_desktop']/div[@class='header_desktop_wrapper']/nav[@class='header_navigation']/div/div[@class='wrapper']/div/div/div[1]/div/ul/li[2]/div/div/div/div[2]/ul/li[4]/a[@title='Ports']"));
		portButton.click();
		Thread.sleep(10000);

		// WHEN I search for “Honolulu” port
		WebElement portSearch = driver.findElement(By.xpath("//html[@id='port-of-call-page']//input[@id='searchbar']"));
		portSearch.sendKeys("Honolulu");

		WebElement option = driver.findElement(
				By.xpath("//html[@id='port-of-call-page']//div[@id='searchArea']//a[@title='Honolulu, Oahu']"));
		option.click();
		
		//THEN Map zoomed to show selected port
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)");

	}

}