import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Parameters {

	WebDriver driver = new ChromeDriver();

	String website = "https://ae.almosafer.com/en";

	Random rand = new Random();

	String ExpectedLanguage = "en";

	String ExpectedCurrency = "SAR";

	String ExpectedNumber = "+966554400000";

	Boolean ExpectedResultForFooter = true;

	String ExpectedValueForHotelTabSelected = "false";

	int tomorrow = LocalDate.now().plusDays(1).getDayOfMonth();

	String ExpectedDeparture = String.format("%02d", tomorrow);

	int dayAfterTomorrow = LocalDate.now().plusDays(2).getDayOfMonth();

	String ExpectedArrival = String.format("%02d", dayAfterTomorrow);

	String[] myWebsites = { "https://ae.almosafer.com/en", "https://ae.almosafer.com/ar" };

	String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
	String[] ArabicCities = { "دبي", "جدة" };

	int randomEnglishIndex = rand.nextInt(EnglishCities.length);
	int randomArabicIndex = rand.nextInt(ArabicCities.length);

	int RandomIndex = rand.nextInt(myWebsites.length);

	Boolean ExpectedValueOfPageIsLoaded = true;

	public void SetupToEnterWebsite() {
		driver.manage().window().maximize();
		driver.get(website);
	}

	public void affirmCurrencyToBeSAR() throws InterruptedException {
		WebElement currencyButton = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));
		currencyButton.click();
		WebElement container = driver.findElement(By.cssSelector(".sc-cTjmhe.eXMQfD"));
		Thread.sleep(1000);
		WebElement currencySAR = container.findElement(By.xpath("//a[@data-testid='CurrencySelection__SAR']"));
		currencySAR.click();
	}

	public void CheckTheWebsiteLanguage(WebElement HotelSearchBar) throws InterruptedException {
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
	}

	public void RandomlyEnterWebsite() {
		driver.get(myWebsites[RandomIndex]);

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
	}

	public void EnterNumOfVistorsThenSearch() {
		WebElement CititesList = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		CititesList.findElements(By.tagName("li")).get(1).click();

		WebElement SelectNumberOfVisitors = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select select = new Select(SelectNumberOfVisitors);

		int RandomVisitor = rand.nextInt(2);

		select.selectByIndex(RandomVisitor);

		WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		searchButton.click();

	}

	public void SortOption() {
		WebElement container = driver.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muiltr-1smo8f0"));
		if (driver.getCurrentUrl().contains("en")) {
			List<WebElement> allPricesList = container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muiltr-18vmb2l"));
			int LowestPrice = Integer.parseInt(allPricesList.get(0).getText().replace("SAR ", ""));
			int HighestPrice = Integer.parseInt(allPricesList.getLast().getText().replace("SAR ", ""));

			boolean ActualValue = LowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			Assert.assertEquals(ActualValue, ExpectedValue);

		} else {
			List<WebElement> allPricesList = container.findElements(By.cssSelector(
					".MuiTypography-root.MuiTypography-heading3SemBld.__ds__comp.undefined.muirtl-1l5b3qq"));
			int LowestPrice = Integer.parseInt(allPricesList.get(0).getText().replace("ر.س. ", ""));
			int HighestPrice = Integer.parseInt(allPricesList.getLast().getText().replace("ر.س. ", ""));

			boolean ActualValue = LowestPrice < HighestPrice;
			boolean ExpectedValue = true;

			Assert.assertEquals(ActualValue, ExpectedValue);

		}

	}
}
