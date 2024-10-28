import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();

	String website = "https://ae.almosafer.com/en";

	Random rand = new Random();

	@BeforeTest
	public void mySetup() {
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
	public void RandomlyChangeLang() throws InterruptedException {
		String[] myWebsites = { "https://ae.almosafer.com/en", "https://ae.almosafer.com/ar" };

		String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
		String[] ArabicCities = { "دبي", "جدة" };

		int randomEnglishIndex = rand.nextInt(EnglishCities.length);
		int randomArabicIndex = rand.nextInt(ArabicCities.length);

		int RandomIndex = rand.nextInt(myWebsites.length);

		driver.get(myWebsites[RandomIndex]);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		WebElement HotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		if (driver.getCurrentUrl().equals("https://ae.almosafer.com/en")) {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "en";

			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
			HotelSearchBar.sendKeys(EnglishCities[randomEnglishIndex]);
		} else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			String ExpectedLanguage = "ar";

			Assert.assertEquals(ActualLanguage, ExpectedLanguage);
			HotelSearchBar.sendKeys(ArabicCities[randomArabicIndex]);
		}
		Thread.sleep(2000);
		WebElement CititesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		CititesList.findElements(By.tagName("li")).get(1).click();

		WebElement SelectNumberOfVisitors = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select select = new Select(SelectNumberOfVisitors);

		int RandomVisitor = rand.nextInt(2);

		select.selectByIndex(RandomIndex);

		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();
		
		Thread.sleep(35000);
	}
	
	@Test(priority = 9)
	public void CheckPageIsLoaded() {
		WebElement searchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
		
		Boolean ActualResult = searchResult.getText().contains("found") ||searchResult.getText().contains("مكان");
		
		Boolean ExpectedResult = true;
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		
		
	}
	
	@Test(priority = 10)
	public void CheckSortOption() throws InterruptedException {
		Thread.sleep(10000);
		
		WebElement LowestPriceButton = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		LowestPriceButton.click();
		
		
		List<WebElement> allPrices = driver.findElements(By.cssSelector(".__ds__comp undefined.MuiBox-root.muirtl-1jby15f"));
		
		
		
		
	}
	
	
	
	
	

}
