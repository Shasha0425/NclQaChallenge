package com.ncl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScenarioTwo {

	@Test
	public void destinationsTest() throws Exception {

		System.out.println("Starting DestinationsTest");

		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Maximize browser window
		driver.manage().window().maximize();

		// open ncl.com
		String url = "https://www.ncl.com/";
		driver.get(url);
		System.out.println("Page is opened");

		Thread.sleep(5000);

		// Validate user is a Guest
		WebElement logInButton = driver.findElement(By.linkText("LOG IN"));
		Assert.assertTrue(logInButton.isDisplayed(), "Log In Button is Not Visble");

		// Validate user is on Homepage
		String expectedUrl = "https://www.ncl.com/";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual Url is not the same as expected.");

		// Navigate to Shore Excursions page
		WebElement exploreButton = driver.findElement(By.xpath(
				"/html//header[@id='header']/section[@class='header_desktop']/div[@class='header_desktop_wrapper']/nav[@class='header_navigation']/div//div[@class='container']/div/div[1]/div/ul//a[@title='Explore']"));
		exploreButton.click();

		Thread.sleep(2000);
		
		WebElement shorexButton = driver.findElement(By.xpath(
				"/html//header[@id='header']/section[@class='header_desktop']/div[@class='header_desktop_wrapper']/nav[@class='header_navigation']/div/div[@class='wrapper']/div/div/div[1]/div/ul/li[2]/div/div/div/div[2]/ul/li[6]/a[@title='Shore Excursions']"));
		shorexButton.click();

		// Search by Destination
		WebElement destinationField = driver
				.findElement(By.xpath("//html[@id='shore-excursions-page']//div[@id='search_destinations_chosen']//b"));
		Thread.sleep(2000);
		destinationField.click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"search_destinations_chosen\"]/div/ul/li[3]")).click();

		WebElement searchExecution = driver.findElement(
				By.xpath("//*[@id=\"page-shore-excursions\"]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/button"));
		searchExecution.click();
		Thread.sleep(5000);

		// Filter results by Ports

		WebElement selectPorts = driver
				.findElement(By.xpath("//*[@id=\"sap-menu-left\"]/div/div[4]/ul[1]/li[2]/a/span"));
		selectPorts.click();
		Thread.sleep(2000);

		var portValues = driver.findElements(By.xpath("//*[@id=\"ports\"]/li"));

		for (WebElement singlePortValue : portValues) {

			if (singlePortValue.getText().contains("Alaska")
					|| singlePortValue.getText().contains("British Columbia")) {
				singlePortValue.click();
			}

		}

		WebElement updatefilter = driver.findElement(
				By.xpath("//*[@id=\"sap-menu-left\"]/div/div[4]/ul[1]/li[2]/div[1]/div/div[4]/ul/li[2]/a"));
		updatefilter.click();

	}

}
