package testcases;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC12_Carwale {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://www.carwale.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2) Click on Used

		driver.findElementByCssSelector("ul#newUsedSearchOption>li:nth-of-type(2)").click();

		// 3) Select the City as Chennai

		driver.findElementById("usedCarsList").sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElementByXPath("//ul[@id='ui-id-10']//a//strong[contains(text(),'Chennai')]").click();

		// 4) Select budget min (8L) and max(12L) and Click Search

		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
		driver.findElementById("btnFindCar").click();
		Thread.sleep(2000);

		// 5) Select Cars with Photos under Only Show Cars With
		// 6) Select Manufacturer as "Hyundai" --> Creta
		// 7) Select Fuel Type as Petrol

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//span[text()='Cars with Photos']").click();
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		js.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//span[text()=' Hyundai ']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Creta']").click();
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//h3[text()[normalize-space()='Fuel Type']]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//ul//li[@name='Petrol']//span)[1]").click();
		Thread.sleep(2000);

		// 8) Select Best Match as "KM: Low to High"

		driver.findElementById("sort").click();
		WebElement sort = driver.findElementById("sort");
		Select srt = new Select(sort);
		srt.selectByVisibleText("KM: Low to High");

		// 9) Validate the Cars are listed with KMs Low to High

		List<WebElement> carpr = driver.findElementsByXPath("(//span[@class='slkms vehicle-data__item'])");

		int carpricee[] = new int[carpr.size()];
		int carprices[] = new int[carpr.size()];

		for (int i = 0; i < carpr.size(); i++) {
			int carprice = Integer.parseInt(carpr.get(i).getText().replaceAll("[^0-9]", ""));
			System.out.println("Price of car is " + carprice);
			carpricee[i] = carprice;
			carprices[i] = carprice;
		}

		Arrays.sort(carpricee);
		for (int i = 0; i < carpr.size(); i++) {
			System.out.println(carpricee[i]);

		}

		if (Arrays.equals(carpricee, carprices)) {
			System.out.println("Cars are sorted accordingly with KMs Low to High");

		} else
			System.out.println("Cars are not sorted accordingly with KMs Low to High");

		// 10) Add the least KM ran car to Wishlist

		for (int i = 0; i < carpr.size(); i++) {
			if (carprices[i] == (carpricee[0])) {
				i = i + 1;
				WebElement ele = driver
						.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])[" + i + "]");
				js.executeScript("arguments[0].click()", ele);
				break;
			}
		}

		driver.findElementByXPath("//li[text()='& Compare']").click();

		// 11) Go to Wishlist and Click on More Details

		driver.findElementByPartialLinkText("More details »").click();
		Set<String> windows = driver.getWindowHandles();
		List<String> lst = new ArrayList<String>(windows);
		driver.switchTo().window(lst.get(1));

		// 12) Print all the details under Overview

		List<WebElement> model = driver.findElements(By.xpath("//div[@class='overview-list padding-bottom10']//li"));

		System.out.println();
		System.out.println("********* OverView Details **********");
		for (int k = 0; k <= model.size(); k++) {

			List<WebElement> cols = driver
					.findElements(By.xpath("//div[@class='overview-list padding-bottom10']//li[" + k + "]//div"));

			for (int i = 0; i < cols.size(); i++) {

				System.out.format("%-30s", cols.get(i).getText());

			}

			System.out.println();

		}

		// 13) Close the browser

		driver.quit();

	}

}
