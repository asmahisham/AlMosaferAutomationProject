import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();

	String website = "https://ae.almosafer.com/en";
	
	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(website);

	}

	@Test(priority = 1)
	public void CheckEnglishLangIsDefault() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLanguage = "en";

		Assert.assertEquals(ActualLanguage, ExpectedLanguage);

	}

	@Test(priority = 2)
	public void CheckDefaultCurrencyIsSAR() {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		String ExpectedCurrency = "SAR";

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();
		String ExpectedNumber = "+966554400000";

		Assert.assertEquals(ActualNumber, ExpectedNumber);

	}

	@Test(priority = 4)
	public void CheckAppleLogoIsInFooter() {
		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		Boolean ActualResult = TheFooter.findElement(By.cssSelector(".sc-dznXNo.ipskEM")).isDisplayed();
		Boolean ExpectedResult = true;

		Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ExpectedValue = "false";
		String ActualValue = HotelTab.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 6)
	public void CheckDepartureDate() throws InterruptedException {
		int today = LocalDate.now().getDayOfMonth();

		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

		String ActualDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		String ExpectedDeparture = Integer.toString(tomorrow);

		Assert.assertEquals(ActualDate, ExpectedDeparture);
	}

	@Test(priority = 7)
	public void CheckArrivalDate() {
		int today = LocalDate.now().getDayOfMonth();
		int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

		int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();

		String ActualArrival = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();
		
		String ExpectedArrival = Integer.toString(dayAfterTomorrow);
		
		Assert.assertEquals(ActualArrival, ExpectedArrival);

	}
	
	@Test(priority = 8)
	public void RandomlyChangeLang() {
		String []myWebsites = {"https://ae.almosafer.com/en","https://ae.almosafer.com/ar"};
		
		int RandomIndex =rand.nextInt(2);
		
		driver.get(myWebsites[RandomIndex]);
		
		if(driver.getCurrentUrl().equals("https://ae.almosafer.com/en")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";

			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		}else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";

			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
		}
	}

}
