package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC16_Ajio {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to www.ajio.com/shop/sale

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/sale");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2) Enter Bags in the Search field and Select Bags in Women Handbags

		driver.findElementByName("searchVal").sendKeys("Bags", Keys.ENTER);
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@for='Women']").click();
		Thread.sleep(2000);

		// 3) Click on five grid and Select SORT BY as "What's New"

		driver.findElementByClassName("five-grid").click();
		Thread.sleep(2000);
		WebElement sort = driver.findElementByTagName("select");
		Select srt = new Select(sort);
		srt.selectByVisibleText("What's New");
		Thread.sleep(4000);

		// 4) Enter Price Range Min as 2000 and Max as 5000

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='price']")));

		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementById("minPrice").sendKeys("2000");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		Thread.sleep(4000);

		// 5) Click on the product "Puma Ferrari LS Shoulder Bag"

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Ferrari LS Shoulder Bag']")));

		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Set<String> windows = driver.getWindowHandles();
		List<String> lt = new ArrayList<String>(windows);
		driver.switchTo().window(lt.get(1));

		// 6) Verify the Coupon code for the price above 2690 is applicable for your
		// product, if applicable the get the Coupon Code and Calculate the discount
		// price for the coupon

		String pr = driver.findElementByClassName("prod-sp").getText().replaceAll("[^0-9]", "");
		int prss = Integer.parseInt(pr);
		System.out.println("Price of Product = " + prss);

		String dispr = driver.findElementByClassName("promo-desc").getText();
		System.out.println(dispr);
		String[] prc = dispr.split("on", 2);
		int prs = Integer.parseInt(prc[1].replaceAll("[^0-9]", ""));
		System.out.println("Price of discount = " + prs);
		int per = Integer.parseInt(prc[0].replaceAll("[^0-9]", ""));
		System.out.println("Discount percentage = " + per);

		String discd = driver.findElementByXPath("//div[@class='promo-title']").getText();
		String discd1 = discd.replaceAll("Use", "");
		String discd2 = discd1.replaceAll("Code", "");
		String discd3 = discd2.replaceAll("\\s", "");

		if (prss > prs) {
			System.out.println("Coupon is applicable");
			System.out.println("Code is ****" + discd3 + "**");

		} else {
			System.out.println("Discount is not applicable");
		}

		System.out.println("Calculating the Discount Price");
		float pers = per / 100.0f;
		float disprc = prss * pers;
		long disp = Math.round(disprc);
		System.out.println("Discount price = " + disp);
		long disprce = prss - disp;
		System.out.println("Price after discount =" + disprce);

		Thread.sleep(2000);

		// 7) Check the availability of the product for pincode 560043, prnt the
		// expected delivery date if it is available

		driver.findElementByCssSelector("div#edd>div>div>span:nth-of-type(2)").click();
		Thread.sleep(2000);
		driver.findElementByName("pincode").sendKeys("600044");
		driver.findElementByClassName("edd-pincode-modal-submit-btn").click();
		Thread.sleep(2000);

		// Disabled pin code as it was not working in application

		// if(driver.findElementByXPath("//ul[@class='edd-message-success-details']//li[1]").isDisplayed())
		// {
		// String deldt =
		// driver.findElementByXPath("//ul[@class='edd-message-success-details']//li[1]//span").getText();
		// System.out.println("Delivery Date is "+deldt);
		// }

		// 8) Click on Other Informations under Product Details and Print the Customer
		// Care address, phone and email

		driver.findElementByClassName("other-info-toggle").click();
		String add = driver.findElementByXPath("//span[text()='Customer Care Address']/following-sibling::span[2]")
				.getText();
		System.out.println("Customer Care Address:");
		System.out.println(add);

		// 9) Click on ADD TO BAG and then GO TO BAG

		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@class='ic-cart ']").click();
		Thread.sleep(2000);

		// 10) Check the Order Total before apply coupon

		String ortt = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		String[] oramtt = ortt.split("Rs.", 2);
		System.out.println("Order Total before applying coupon = " + oramtt[1]);

		// 11) Enter Coupon Code and Click Apply

		driver.findElementById("couponCodeInput").sendKeys(discd3);
		driver.findElementByXPath("//button[text()='Apply']").click();
		Thread.sleep(2000);

		// 12) Verify the Coupon Savings amount(round off if it in decimal) under Order
		// Summary and the matches the amount calculated in Product details

		String saamt = driver.findElementByCssSelector("section#couponDiscount>span:nth-of-type(2)").getText();
		String[] amtt = saamt.split("Rs.", 2);
		float smt = Float.parseFloat(amtt[1]);
		long samt = Math.round(smt);
		System.out.println("Coupon savings amount = " + samt);

		if (samt == disp) {
			System.out.println("Discount price matches");
		} else {
			System.out.println("Discount price does not match");
		}
		Thread.sleep(2000);

		// 13) Click on Delete and Delete the item from Bag

		driver.findElementByClassName("delete-btn").click();
		driver.findElementByXPath("//div[text()='DELETE']").click();
		String empmg = driver.findElementByClassName("empty-msg").getText();
		System.out.println(empmg);

		// 14) Close all the browsers

		driver.quit();

	}

}
