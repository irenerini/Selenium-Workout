package testcases;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC17_Microsoft {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://azure.microsoft.com/en-in/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://azure.microsoft.com/en-in/");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2) Click on Pricing

		driver.findElementById("navigation-pricing").click();
		Thread.sleep(2000);
		// 3) Click on Pricing Calculator

		driver.findElementByLinkText("Pricing calculator").click();
		Thread.sleep(2000);

		// 4) Click on Containers

		driver.findElementByCssSelector(
				"section#products-picker-panel>div:nth-of-type(2)>div:nth-of-type(2)>div:nth-of-type(7)>button")
				.click();

		// 5) Select Container Instances

		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();

		// 6) Click on Container Instance Added View

		driver.findElementById("new-module-loc").click();
		Thread.sleep(2000);

		// 7) Select Region as "South India"

		WebElement region = driver.findElementByXPath("//select[@aria-label='Region']");
		Select reg = new Select(region);
		reg.selectByVisibleText("South India");
		Thread.sleep(2000);

		// 8) Set the Duration as 180000 seconds

		driver.findElementByXPath("//input[@aria-label='Seconds']").sendKeys(Keys.chord(Keys.CONTROL, "a"), "180000");
		Thread.sleep(2000);

		// 9) Select the Memory as 4GB

		WebElement mem = driver.findElementByXPath("//select[@aria-label='Memory']");
		Actions builder = new Actions(driver);

		Select mm = new Select(mem);
		mm.selectByVisibleText("4 GB");
		Thread.sleep(2000);

		// 10) Enable SHOW DEV/TEST PRICING

		WebElement tog = driver.findElementByXPath("//button[@name='devTestSelected']");
		tog.click();
		Thread.sleep(2000);

		// 11) Select Indian Rupee as currency

		WebElement cur = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select curn = new Select(cur);
		curn.selectByIndex(27);
		Thread.sleep(2000);

		// 12) Print the Estimated monthly price

		String cost = driver
				.findElementByXPath(
						"(//div[@class='column large-3 text-right total']//span[@class='numeric']//span)[2]")
				.getText().replaceAll("[^0-9.]", "");
		System.out.println("Estimated monthly price = " + cost);

		// 13) Click on Export to download the estimate as excel

		driver.findElementByXPath("//button[text()='Export']").click();
		Thread.sleep(2000);

		// 14) Verify the downloded file in the local folder

		if (new File("C:\\Users\\Irene\\Downloads\\ExportedEstimate.xlsx").exists()) {
			System.out.println("Downloaded file exists in downloaded folder");
		} else {
			System.out.println("Downloaded file does not exists in downloaded folder");

		}

		// 15) Navigate to Example Scenarios and Select CI/CD for Containers

		Thread.sleep(2000);
		WebElement exsce = driver.findElementByXPath("//li[@id='solution-architectures-picker']");
		builder.moveToElement(exsce).click().perform();
		driver.findElementByXPath("(//button[@class='solution-architecture-item false'])[2]").click();
		Thread.sleep(2000);

		// 16) Click Add to Estimate

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");

		driver.findElementByXPath("//button[contains(@class,'button button--secondary01')]").click();
		Thread.sleep(3000);

		// 17) Change the Currency as Indian Rupee

		WebElement curr = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currn = new Select(curr);
		currn.selectByIndex(27);
		Thread.sleep(2000);

		// 18) Enable SHOW DEV/TEST PRICING

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='devTestSelected']")));
		
		WebElement togg = driver.findElementByXPath("//button[@name='devTestSelected']");
		togg.click();
		Thread.sleep(2000);

		// 19) Export the Estimate

		driver.findElementByXPath("//button[text()='Export']").click();
		Thread.sleep(2000);

		// 20) Verify the downloded file in the local folder

		if (new File("C:\\Users\\Irene\\Downloads\\ExportedEstimate (1).xlsx").exists()) {
			System.out.println("Downloaded Estimate file exists in downloaded folder");
		} else {
			System.out.println("Downloaded Estimate file does not exists in downloaded folder");

		}
	}

}
