import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class testcases {

	WebDriver driver = new ChromeDriver();
	String Url = "https://global.almosafer.com/ar";
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();
		int RandomURLINDEX = rand.nextInt(2);
		String[] myURLS = { "https://global.almosafer.com/ar", "https://global.almosafer.com/en" };

		driver.get(myURLS[RandomURLINDEX]);

		if (driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div")).isDisplayed()) {
			driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();

		}

	}

	@Test(groups = "mid", enabled = false)
	public void CheckTheLanguage() {

		if (driver.getCurrentUrl().contains("ar")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

			Assert.assertEquals(ActualLanguage, "ar");

		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			System.out.println(ActualLanguage);
			Assert.assertEquals(ActualLanguage, "en");

		}
	}

	@Test(groups = "low")
	public void checkTheCurrency() {

		WebElement CurrencyTab = driver
				.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/div[1]/div/button"));
		String ActualCurrency = CurrencyTab.getText();
		Assert.assertEquals(ActualCurrency, "SAR");
	}

	@Test(groups = "high")
	public void checkTheContactNumber() {
		WebElement ContactNumberTab = driver.findElement(By.xpath("//*[@id=\"__next\"]/header/div/div[1]/div[2]/div/a[2]/strong"));
		String ActualContactNumber = ContactNumberTab.getText();
    Assert.assertEquals(ActualContactNumber, "+966554400000"); 
	}

	
	
	
	@Test(invocationCount = 5)
	public void CheckTheHotel() {

		driver.get("https://www.almosafer.com/en/hotels-home");
		WebElement xbath =driver
				.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div[4]/div/div/div/div[1]/div/div[1]/div/div/input"));
		xbath.click();
		int myRandomIndex = rand.nextInt(3);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		
		if (driver.getCurrentUrl().contains("ar")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(ActualLanguage, "ar");
			String[] myIDs = { "Dubai", "Jeddah", "Riyadh" };


	        // Select a location based on the random index
	        String selectedLocation = myIDs[myRandomIndex];
	        
	        xbath.sendKeys(selectedLocation);

			

		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			//System.out.println(ActualLanguage);
			String[] myIDs = { "دبي", "جده", "رياض" };
			   // Select a location based on the random index
	        String selectedLocation = myIDs[myRandomIndex];
	        
	        xbath.sendKeys(selectedLocation);
	        driver.findElement(By.xpath("//*[@id=\"__next\"]/section[2]/div[4]/div/div[2]/div/div[4]/button")).click();


		}
				
				
	}
	
	@AfterTest
	public void postTesting() {
		
		
	}


}