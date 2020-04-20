package testcases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jsoup.select.Evaluator.ContainsText;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC4HP_Website {

	public static ChromeDriver driver;

	public void closepp() {

		try {

			WebElement ppcls = driver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']");
			ppcls.click();
			// Thread.sleep(1000);

		} catch (Exception e) {

		}

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://store.hp.com/in-en/

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);

		options.merge(cap);

		driver = new ChromeDriver(options);
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		TC4HP obj = new TC4HP();

		// 2) Mouse over on Laptops menu and click on Pavilion

		WebElement laptops = driver.findElementByXPath("(//span[text()='Laptops'])[1]");
		Actions builder = new Actions(driver);
		builder.moveToElement(laptops).perform();

		WebElement pavilion = driver.findElementByXPath("(//span[text()='Pavilion'])[1]");
		builder.click(pavilion).perform();
		Thread.sleep(2000);

		driver.findElementByXPath("//button[@aria-label='Accept Cookies']").click();
		Thread.sleep(4000);

		// 3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7

		obj.closepp();
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-800)");
		obj.closepp();
		WebElement opt = driver.findElementByXPath("//span[text()='Intel Core i7']");
		opt.click();
		Thread.sleep(3000);

		// 4) Hard Drive Capacity -->More than 1TB

		obj.closepp();
		driver.findElementByXPath("//span[text()='More than 8 GB']").click();
		Thread.sleep(2000);

		// 5) Select Sort By: Price: Low to High

		obj.closepp();
		WebElement sort = driver.findElementByXPath("(//select[@class='sorter-options option-new'])[1]");
		sort.click();
		Select sorts = new Select(sort);
		obj.closepp();
		sorts.selectByVisibleText("Price : Low to High");
		Thread.sleep(2000);

		// 6) Print the First resulting Product Name and Price

		obj.closepp();
		WebElement pdname = driver.findElementByXPath("(//a[@class='product-item-link'])[1]");
		System.out.println("Product Name is " + pdname.getText().replaceAll("[^a-zA-Z0-9_-]", ""));
		obj.closepp();
		WebElement pdpr = driver.findElementByXPath(
				"//span[@class='price-container price-final_price tax weee']//span[@class='price']");
		System.out.println("Product Price is " + pdpr.getText().replaceAll("[^a-zA-Z0-9_-]", ""));

		// 7) Click on Add to Cart

		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		obj.closepp();
		js1.executeScript("window.scrollBy(0,1000)");
		obj.closepp();
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		Thread.sleep(2000);

		// 8) Click on Shopping Cart icon --> Click on View and Edit Cart

		obj.closepp();
		driver.findElementByXPath("//a[@class='action showcart']").click();
		obj.closepp();
		driver.findElementByXPath("//span[text()='View and edit cart']").click();

		// 9) Check the Shipping Option --> Check availability at Pincode

		obj.closepp();
		driver.findElementByName("pincode").sendKeys("600044");
		obj.closepp();
		driver.findElementByCssSelector("div#product-deliver>div>button").click();
		obj.closepp();
		WebElement av = driver.findElementByXPath("//button[@class='primary standard_delivery-button change']");
		System.out.println(av.getText());
		Thread.sleep(1000);
		if ((av.getText()).contains("CHANGE")) {
			System.out.println("Pincode is avialable");
		} else {
			System.out.println("Pincode is not avialable");
		}

		// 10) Verify the order Total against the product price

		obj.closepp();
		WebElement pprice = driver.findElementByXPath("(//span[@class='cart-price']//span)[3]");
		Thread.sleep(1000);
		System.out.println("Product Price is " + pprice.getText().replaceAll("[^a-zA-Z0-9_-]", ""));

		// 11) Proceed to Checkout if Order Total and Product Price matches

		obj.closepp();
		WebElement price = driver.findElementByXPath("(//span[@class='price'])[9]");
		System.out.println("Order Price is " + price.getText().replaceAll("[^a-zA-Z0-9_-]", ""));
		if ((price.getText().replaceAll("[^a-zA-Z0-9_-]", ""))
				.contentEquals(pprice.getText().replaceAll("[^a-zA-Z0-9_-]", ""))) {
			System.out.println("Product price match Order price");
			obj.closepp();
			driver.findElementByXPath("(//span[text()='Proceed to Checkout'])[1]").click();
		} else {
			System.out.println("Product price do not match Order price");

		}

		// 12) Click on Place Order

		obj.closepp();
		driver.findElementByXPath("(//span[text()='Place Order'])[4]").click();

		// 13) Capture the Error message and Print

		WebElement errmsg = driver.findElementById("customer-email-error");
		System.out.println("Error message displayed as " + errmsg.getText());

		// 14) Close Browser

		driver.close();

	}

}
