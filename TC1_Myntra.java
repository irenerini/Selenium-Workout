package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC1_Myntra {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Mouse over on WOMEN, Click Jackets & Coats
		WebElement women = driver.findElementByXPath("//a[text()='Women']");

		Actions builder = new Actions(driver);
		builder.moveToElement(women).perform();
		Thread.sleep(2000);
		WebElement jc = driver.findElementByLinkText("Jackets & Coats");
		builder.click(jc).perform();

		// Get count of J&C

		WebElement titlecount = driver.findElementByClassName("title-count");
		String count = titlecount.getText();
		String[] ct = count.split("\\s", 0);
		int cnt = Integer.parseInt(ct[1]);
		System.out.println("Count of total Items " + cnt);

		WebElement j1 = driver.findElementByXPath("(//span[@class='categories-num'])[1]");
		String jc1 = j1.getText();
		String jt = jc1.replaceAll("[^a-zA-Z0-9_-]", "");
		int jct = Integer.parseInt(jt);
		System.out.println("Count of Jacket Items " + jct);

		WebElement c1 = driver.findElementByXPath("(//span[@class='categories-num'])[2]");
		String cc1 = c1.getText();
		String cct = cc1.replaceAll("[^a-zA-Z0-9_-]", "");
		int ct1 = Integer.parseInt(cct);
		System.out.println("Count of Coat Items " + ct1);

		// Validate the sum of categories count matches

		int sum = jct + ct1;
		if (cnt == sum) {
			System.out.println("Count of Total items matches with Sum of Jackets & Coats");
		} else {
			System.out.println("Failed");
		}

		// Check Coats, Brand MANGO and close pop-up

		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		driver.findElementByClassName("brand-more").click();
		Thread.sleep(2000);
		driver.findElementByClassName("FilterDirectory-searchInput").sendKeys("MANGO");
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@class=' common-customCheckbox']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();

		// Confirm all the Coats are of brand MANGO
		List<WebElement> brandnm = driver.findElementsByXPath("//div[@class='product-productMetaInfo']//h3");
		System.out.println(brandnm.size());
		for (WebElement brname : brandnm) {
			if (brname.getText().equalsIgnoreCase("MANGO")) {
				// System.out.println("Brand name is same");
			} else {
				System.out.println("Brand name is not same");
			}
		}

		// Sort by Better Discount
		WebElement sort = driver.findElementByClassName("sort-sortBy");

		builder.moveToElement(sort).perform();
		Thread.sleep(2000);
		WebElement disc = driver.findElementByXPath("//label[contains(text(),'Better Discount')]");
		builder.click(disc).perform();
		Thread.sleep(2000);

		// Find the price of first displayed item

		List<WebElement> prlt = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		System.out.println("Price of first item " + prlt.get(0).getText());

		// Mouse over on size of the first item

		WebElement frst = driver.findElementByXPath("(//div[@class='product-productMetaInfo'])[1]");
		builder.moveToElement(frst).perform();
		Thread.sleep(2000);

		// Click on WishList Now
		driver.findElementByXPath("//span[text()='Wishlist']").click();

		driver.close();

	}

}
