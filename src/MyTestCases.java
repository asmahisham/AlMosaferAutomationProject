import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

	@BeforeTest
	public void mySetup() {

		SetupToEnterWebsite();

	}

	@Test(priority = 1)
	public void CheckEnglishLangIsDefault() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedLanguage);

	}

	@Test(priority = 2)
	public void CheckDefaultCurrencyIsSAR() throws InterruptedException {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

		affirmCurrencyToBeSAR();
	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ActualNumber = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG")).getText();

		Assert.assertEquals(ActualNumber, ExpectedNumber);

	}

	@Test(priority = 4)
	public void CheckAppleLogoIsInFooter() {
		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		Boolean ActualResult = TheFooter.findElement(By.cssSelector(".sc-dznXNo.ipskEM")).isDisplayed();

		Assert.assertEquals(ActualResult, ExpectedResultForFooter);

	}

	@Test(priority = 5)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValue = HotelTab.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, ExpectedValueForHotelTabSelected);

	}

	@Test(priority = 6)
	public void CheckDepartureDate() throws InterruptedException {
		// int today = LocalDate.now().getDayOfMonth();

		String ActualDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(ActualDate, ExpectedDeparture);
	}

	@Test(priority = 7)
	public void CheckArrivalDate() {
		// int today = LocalDate.now().getDayOfMonth();
		// int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

		String ActualArrival = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"))
				.getText();

		Assert.assertEquals(ActualArrival, ExpectedArrival);

	}

	@Test(priority = 8)
	public void RandomlyChangeLang() throws InterruptedException {
		RandomlyEnterWebsite();

		WebElement HotelSearchBar = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		CheckTheWebsiteLanguage(HotelSearchBar);

		EnterNumOfVistorsThenSearch();
		Thread.sleep(35000);
	}

	@Test(priority = 9)
	public void CheckPageIsLoaded() {
		WebElement searchResult = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));

		Boolean ActualResult = searchResult.getText().contains("found") || searchResult.getText().contains("مكان");

		Assert.assertEquals(ActualResult, ExpectedValueOfPageIsLoaded);

	}

	@Test(priority = 10)
	public void CheckSortOption() throws InterruptedException {
		// Thread.sleep(10000);

		WebElement LowestPriceButton = driver.findElement(By.xpath("//div[@data-testid='srp_sort_LOWEST_PRICE']"));
		LowestPriceButton.click();

		Thread.sleep(2000);

		SortOption();
	}

}
