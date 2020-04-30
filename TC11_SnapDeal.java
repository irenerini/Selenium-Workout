package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC11_SnapDeal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://www.snapdeal.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// ‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys

		Actions builder = new Actions(driver);
		WebElement toys = driver
				.findElementByCssSelector("div#leftNavMenuRevamp>div>div:nth-of-type(2)>ul>li:nth-of-type(9)>a>span");
		builder.moveToElement(toys).perform();

		WebElement toy = driver.findElementByXPath("//span[text()='Toys']");
		builder.moveToElement(toy).click().perform();

		// 3) Click Educational Toys in Toys & Games and discount

		driver.findElementByXPath("//div[text()='Educational Toys']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//label[@for='avgRating-4.0']").click();
		Thread.sleep(3000);

		// 5) Click the offer as 40-50

		driver.findElementByXPath("//input[@value='40%20-%2050']/following-sibling::label[1]").click();

		// 6) Check the availability for the pincode, click Quick View of first product

		driver.findElementByXPath("(//input[@class='sd-input'])[2]").sendKeys("600044");
		driver.findElementByXPath("//button[text()='Check']").click();
		if (!driver.findElementByXPath("//div[@class='serviceable-err hidden']//p[contains(text(),'Sorry!')]")
				.isEnabled()) {
			System.out.println("Pincode is wrong");

		} else {

			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");

			WebElement firstitem = driver
					.findElementByXPath("//div[@class='col-xs-6  favDp product-tuple-listing js-tuple ']");
			builder.moveToElement(firstitem).perform();

			driver.findElementByXPath("//div[contains(text(),'Quick View')]").click();

		}

		// 7) Click the Quick View of the first product
		// 8) Click on View Details

		Thread.sleep(2000);
		driver.findElementByXPath("//a[@class=' btn btn-theme-secondary prodDetailBtn']").click();

		// 9) Capture the Price of the Product and Delivery Charge

		String price = driver.findElementByXPath("//span[@itemprop='price']").getText();
		int prc = Integer.parseInt(price);
		System.out.println("Price of the first product = " + prc);

		String dchrge = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String[] chrg = dchrge.split("Rs", 2);
		int charge = Integer.parseInt(chrg[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		System.out.println("Delivery charges applicable = " + charge);

		int totalpay = prc + charge;
		System.out.println("Total Pay of first product " + totalpay);

		// 10) Validate the You Pay amount matches the sum of (price+deliver charge)

		driver.findElementById("add-cart-button-id").click();
		String pay = driver.findElementByXPath("//div[@class='you-pay']//span[1]").getText();
		String[] ttpay = pay.split("Rs.", 2);
		int tpay = Integer.parseInt(ttpay[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		System.out.println("Price of the first product = " + tpay);

		if (totalpay == tpay) {
			System.out.println("You Pay amount matches the sum of (Price + Deliver charge)");
		} else {
			System.out.println("You Pay amount does not match the sum of (Price + Deliver charge)");
		}

		// 11) Search for Sanitizer

		driver.findElementById("inputValEnter").sendKeys("sanitizer", Keys.ENTER);

		// 12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"

		driver.findElementByXPath("//p[text()='BioAyurveda Neem Power  Hand Sanitizer 500 mL Pack of 1']").click();
		Set<String> windows = driver.getWindowHandles();
		List<String> lt = new ArrayList<String>(windows);
		driver.switchTo().window(lt.get(1));

		// 13) Capture the Price and Delivery Charge

		String pricea = driver.findElementByXPath("//span[@itemprop='price']").getText();
		int prca = Integer.parseInt(pricea);
		System.out.println("Price of the second product = " + prca);

		String dchrgee = driver.findElementByXPath("(//span[@class='availCharges'])[2]").getText();
		String[] chrge = dchrge.split("Rs", 2);
		int chargee = Integer.parseInt(chrge[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		System.out.println("Delivery charges = " + chargee);

		int totalpayy = prca + chargee;
		System.out.println("Total Pay of second product is " + totalpayy);
		Thread.sleep(2000);

		// 14) Click on Add to Cart

		driver.findElementByXPath("(//span[text()='ADD TO CART'])[1]").click();
		Thread.sleep(2000);

		// 15) Click on Cart

		driver.findElementByXPath("//i[@class='sd-icon sd-icon-cart-icon-white-2']").click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rtbScriptContainer>div>span>i")));
		WebElement cart = driver.findElementByXPath("(//span[@class='item-subtotal-black'])[1]");
		String subt = cart.getText();
		String[] ta = subt.split("Rs.", 2);
		int tt = Integer.parseInt(ta[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		System.out.println("****After checking out on both Products****");
		System.out.println("Price of the first product = " + tt);

		WebElement cart1 = driver.findElementByXPath("(//span[@class='item-subtotal-black'])[4]");
		String subtt = cart1.getText();
		String[] tta = subtt.split("Rs.", 2);
		int ttt = Integer.parseInt(tta[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		System.out.println("Price of the second product = " + ttt);

		// 16) Validate the Proceed to Pay matches the total amount of both the products

		String ppay = driver.findElementByCssSelector("form#checkout-continue>input:nth-of-type(2)")
				.getAttribute("value");
		System.out.println(ppay);
		String[] py = ppay.split("Rs.", 2);
		int pays = Integer.parseInt(py[1].replaceAll("[^a-zA-Z0-9_-]", ""));
		System.out.println("Price of the total pay of products = " + pays);

		if (pays == (tt + ttt)) {
			System.out.println("You Pay amount matches the sum of cart item prices");
		} else {
			System.out.println("You Pay amount does not match the sum of cart item prices");
		}

		// 17) Close all the windows

		driver.quit();

	}

}
