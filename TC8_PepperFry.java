package testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC8_PepperFry {

	static ChromeDriver driver;

	public static void closepp() {
		try {
			if (driver.findElementByCssSelector("div#regPopUp>a").isEnabled()) {
				driver.findElementByCssSelector("div#regPopUp>a").click();
			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) throws InterruptedException, WebDriverException, IOException {
		// TODO Auto-generated method stub

		// 1) Go to https://www.pepperfry.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.pepperfry.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByClassName("close").click();
		closepp();

		// 2) Mouseover on Furniture and click Office Chairs under Chairs

		WebElement furniture = driver.findElementByLinkText("Furniture");
		Actions builder = new Actions(driver);
		builder.moveToElement(furniture).perform();
		WebElement ofchairs = driver.findElementByLinkText("Office Chairs");
		builder.moveToElement(ofchairs).click().perform();

		// 3) click Executive Chairs

		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();

		// 4) Change the minimum Height as 50 in under Dimensions

		WebElement height = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
		height.clear();
		height.sendKeys("50", Keys.ENTER);
		Thread.sleep(3000);

		// 5) Add "Poise Executive Chair in Black Colour" chair to Wishlist

		driver.findElementByXPath(
				"(//a[contains(text(),'Poise Executive Chair in Black Colour')]/ancestor::div[@class='clip-dtl-ttl row']/following-sibling::div//a)[2]")
				.click();
		Thread.sleep(2000);

		// 6) Mouseover on Homeware and Click Pressure Cookers under Cookware

		WebElement homeware = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
		builder.moveToElement(homeware).perform();
		WebElement cooker = driver.findElementByLinkText("Pressure Cookers");
		builder.moveToElement(cooker).click().perform();

		// 7) Select Prestige as Brand

		driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();

		// 8) Select Capacity as 1-3 Ltr

		Thread.sleep(2000);
		WebElement ltr = driver.findElementByXPath("//input[@data-value='1 Ltr - 3 Ltr']/following-sibling::label[1]");
		builder.moveToElement(ltr).click().perform();

		// 9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist

		Thread.sleep(2000);
		driver.findElementByXPath(
				"(//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr')]/ancestor::div[@class='clip-dtl-ttl row']/following-sibling::div//a)[2]")
				.click();

		// 10) Verify the number of items in Wishlist

		String wishcount = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		System.out.println("Number of items in Wishlist is " + wishcount);

		// 11) Navigate to Wishlist

		driver.findElementByXPath("//a[contains(@class,'pf-icon pf-icon-heart')]").click();

		// 12) Move Pressure Cooker only to Cart from Wishlist

		Thread.sleep(2000);
		WebElement cart = driver.findElementByXPath(
				"//a[contains(text(),'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By')]/parent::p/ following-sibling::div//a");
		builder.moveToElement(cart).click().perform();

		// 13) Check for the availability for Pincode 600128

		driver.findElementByClassName("srvc_pin_text").sendKeys("600044");
		driver.findElementByClassName("check_available").click();
		Thread.sleep(2000);

		// 14) Click Proceed to Pay Securely

		if (driver.findElementByXPath("(//div[@class='minicart-error'])[1]").isDisplayed()) {

			String errmsg = driver.findElementByXPath("(//div[@class='minicart-error'])[1]").getText();
			System.out.println(errmsg);

		} else {
			driver.findElementByCssSelector("div#minicart_footer>div").click();

		}
		Thread.sleep(2000);

		// 15) Click Proceed to Pay

		driver.findElementByXPath("//span[@class='ck-proceed-btn-wrap']//a[1]").click();

		// 16) Capture the screenshot of the item under Order Item

		driver.findElementByCssSelector("div#ordersummary_accordian_header>div:nth-of-type(2)>span").click();
		Thread.sleep(2000);
		WebElement element = driver
				.findElementByCssSelector("div#ordersummary_accrodian>div:nth-of-type(2)>ul>div>div>li>figure>img");

		FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File("./TC7Pepperfry_ss.png"));

		// 17) Close the browser
		
		driver.quit();

	}

}
