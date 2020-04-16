package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC2Nykaa {

	public static void main(String[] args) throws InterruptedException {

		// Go to https://www.nykaa.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Mouseover on Brands and Mouseover on Popular, Click L'Oreal Paris

		Actions builder = new Actions(driver);
		WebElement brand = driver.findElementByCssSelector("div#headerMenu>ul>li:nth-of-type(2)>a");
		builder.moveToElement(brand).perform();
		Thread.sleep(1000);
		WebElement popular = driver.findElementByXPath("//div[@class='BrandsCategoryHeading']//a[1]");
		builder.moveToElement(popular).perform();
		WebElement loreal = driver.findElementByCssSelector("div#brandCont_Popular>ul>li:nth-of-type(5)>a>img");
		builder.click(loreal).perform();

		// Go to the newly opened window and check the title contains L'Oreal Paris

		Set<String> window = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(window);
		driver.switchTo().window(ls.get(1));
		System.out.println("Window Title is " + driver.getTitle());
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Title is validated");
		} else {
			System.out.println("Title does not match");
		}

		// Click sort By and select customer top rated, Click Category and click Shampoo

		driver.findElementByXPath("//i[@class='fa fa-angle-down']").click();
		driver.findElementByXPath("//span[text()='customer top rated']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Category']").click();
		WebElement filtercatgy = driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined']//span)[1]");
		filtercatgy.click();
		System.out.println(filtercatgy.getText());
		String filter1 = filtercatgy.getText();

		// check whether the Filter is applied with Shampoo
		
		WebElement filter = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']//li[1]");
		String text = filter.getText();
		for (WebElement child : filter.findElements(By.xpath("./*"))) {
			text = text.replaceFirst(child.getText(), "");
		}
		System.out.println("**" + text + "**");

		if (filter1.contains(text)) {
			System.out.println("Filter applied is valid");
		} else {
			System.out.println("Filter is not valid");
		}

		// Click on L'Oreal Paris Colour Protect Shampoo, select size as 175ml

		driver.findElementByXPath("//h2[contains(@title,'Paris Colour Protect Shampoo')]").click();
		Set<String> windows = driver.getWindowHandles();
		List<String> lt = new ArrayList<String>(windows);
		driver.switchTo().window(lt.get(2));
		System.out.println("Window Title is " + driver.getTitle());
		driver.findElementByXPath("//span[text()='175ml']").click();

		// Print the MRP of the product, Click on ADD to BAG, Go to Shopping Bag, Get
		// Grand Total and Proceed

		WebElement price = driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]");
		System.out.println("MRP of Product is " + price.getText().replaceAll("[^a-zA-Z0-9_-]", ""));
		driver.findElementByXPath("(//button[text()='ADD TO BAG'])[1]").click();
		driver.findElementByClassName("AddBagIcon").click();
		Thread.sleep(1000);
		WebElement grdtotal = driver.findElementByXPath("//div[@class='value medium-strong']");
		System.out.println("Grand Total is " + grdtotal.getText().replaceAll("[^a-zA-Z0-9_-]", ""));
		driver.findElementByXPath("//span[text()='Proceed']").click();

		// Click on Continue as Guest, Print the warning message (delay in shipment)

		driver.findElementByXPath("//button[text()='CONTINUE AS GUEST']").click();
		WebElement wrmessage = driver.findElementByClassName("message");
		Thread.sleep(1000);
		System.out.println("Warning Message = " + wrmessage.getText());

		driver.quit();

	}

}
