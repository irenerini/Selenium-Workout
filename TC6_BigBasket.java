package testcases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC6_BigBasket {

	public static ChromeDriver driver;

	public void continuepp() {
		try {
			if (driver.findElementByPartialLinkText("Continue").isEnabled()) {
				driver.findElementByPartialLinkText("Continue").click();
			}

		} catch (Exception e) {

		}
	}

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://www.bigbasket.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TC6_BigBasket obj = new TC6_BigBasket();

		// 2) mouse over on Shop by Category

		WebElement shopdd = driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']");
		Actions builder = new Actions(driver);
		builder.moveToElement(shopdd).click().perform();

		// 3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS

		WebElement food = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		builder.moveToElement(food).perform();
		Thread.sleep(1000);
		WebElement rice = driver.findElementByXPath("(//a[text()='Rice & Rice Products'])[2]");
		builder.moveToElement(rice).click().perform();

		// 4) Click on Boiled & Steam Rice

		driver.findElementByXPath("(//span[text()='Boiled & Steam Rice'])[1]").click();

		// 5) Choose the Brand as bb Royal

		driver.findElementByXPath("//div[@class='col-xs-12 checkbox ng-scope']//span[text()='bb Royal']").click();

		// 6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown

		Thread.sleep(4000);
		driver.findElementByXPath(
				"//a[contains(text(),'Ponni Boiled Rice - Super Premium')]/parent::div/following-sibling::div//button")
				.click();

		WebElement proddp1 = driver.findElementByXPath(
				"(//a[contains(text(),'Ponni Boiled Rice - Super Premium')]/parent::div/following-sibling::div)//span[contains(text(),'5 kg')]");
		builder.moveToElement(proddp1).click().perform();

		// 7) print the price of Rice

		WebElement price = driver.findElementByXPath(
				"(//a[contains(text(),'Ponni Boiled Rice - Super Premium')]/parent::div/following-sibling::div[2]//span[2])[1]");
		System.out.println("Product Price is " + price.getText());

		// 8) Click Add button

		driver.findElementByXPath(
				"(//a[contains(text(),'Ponni Boiled Rice - Super Premium')]/parent::div/following-sibling::div[2]//button)[1]")
				.click();
		obj.continuepp();
		driver.findElementByXPath(
				"(//a[contains(text(),'Idli Rice/Idli Arisi')]/parent::div/following-sibling::div[2]//button)[1]")
				.click();

		// 9) Verify the success message displayed

		WebElement itemaddmsg = driver.findElementByXPath("//div[@class='toast-title']");
		System.out.println("Success message displayed as " + itemaddmsg.getText());

		// 10) Type Dal in Search field and enter

		driver.findElementById("input").sendKeys("Dal", Keys.ENTER);

		// 12) Go to Toor/Arhar Dal and select 2kg & set Qty 2

		driver.findElementByXPath("//a[contains(text(),'Toor/Arhar Dal')]/parent::div/following-sibling::div//button")
				.click();
		Thread.sleep(2000);
		WebElement proddp2 = driver.findElementByXPath(
				"(//a[contains(text(),'Toor/Arhar Dal')]/parent::div/following-sibling::div)//span[contains(text(),'2 kg')]");
		builder.moveToElement(proddp2).click().perform();
		driver.findElementByXPath(
				"(//a[contains(text(),'Toor/Arhar Dal')]/parent::div/following-sibling::div[2]//input)[1]").clear();

		driver.findElementByXPath(
				"(//a[contains(text(),'Toor/Arhar Dal')]/parent::div/following-sibling::div[2]//input)[1]")
				.sendKeys("2");
		Thread.sleep(1000);

		// 13) Print the price of Dal

		WebElement pricedal = driver.findElementByXPath(
				"(//a[contains(text(),'Toor/Arhar Dal')]/parent::div/following-sibling::div[2]//span[2])[1]");
		System.out.println("Product Price of Dal is " + pricedal.getText());

		// 14) Click Add button

		driver.findElementByXPath(
				"(//a[contains(text(),'Toor/Arhar Dal')]/parent::div/following-sibling::div[2]//button)[1]").click();

		WebElement itemaddmsgdal = driver.findElementByXPath("//div[@class='toast-title']");
		System.out.println("Success message displayed as " + itemaddmsgdal.getText());
		driver.findElementByXPath("//button[@class='toast-close-button']").click();

		// 15) Mouse hover on My Basket

		WebElement basket = driver.findElementByClassName("basket-content");
		builder.moveToElement(basket).clickAndHold(basket).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@qa='itemsListMB']//div[@class='row mrp']//span[1])[1]")));
		TC6_BigBasket obt = new TC6_BigBasket();
		
		Float firstProdPrice = obt.getPrice(1);
		Float secondProdPrice = obt.getPrice(2);
		Float thirdProdPrice = obt.getPrice(3);
		System.out.println("hi");
		
		
		// 16) Validate the Sub Total displayed for the selected items
		
		WebElement subt = driver.findElementByXPath("//span[@qa='subTotalMB']");
		float subtotal = Float.parseFloat(subt.getText());
		System.out.println("Sub Total of all products is "+subtotal);
		
		float total = firstProdPrice+secondProdPrice+thirdProdPrice;
		if (subtotal==total) {
			System.out.println("Sub Total matches");
		}
		else {
			System.out.println("Sub Total does not match");
		}
		
		// 17) Reduce the Quantity of Dal as 1
		
		driver.findElementByXPath("(//button[@qa='decQtyMB'])[3]").click();
		Thread.sleep(3000);
		float alteredProdPrice = obt.getPrice(3);
		
		WebElement subt1 = driver.findElementByXPath("//span[@qa='subTotalMB']");
		float subtotal1 = Float.parseFloat(subt1.getText());
		System.out.println("Sub Total of all products after alteration "+subtotal1);
		
		// 18) Validate the Sub Total for the current items
		
		float total1 = firstProdPrice+secondProdPrice+alteredProdPrice;
		if (subtotal1==total1) {
			System.out.println("Sub Total matches");
		}
		else {
			System.out.println("Sub Total does not match");
		}
		
		
		// 19) Close the Browser
		
		driver.close();

	}
	
	public Float getPrice(int prod) {
		
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li[@qa='itemsListMB']//div[@class='row mrp']//span[1])[1]")));
		
		WebElement prodpr = driver.findElementByXPath("(//li[@qa='itemsListMB']//div[@class='row mrp']//span[1])["+prod+"]");
		String prodprice = prodpr.getText();
		float prodPrice = Float.parseFloat(prodprice);
		WebElement quanity = driver.findElementByXPath("(//div[@class='product-qty ng-binding'])["+prod+"]");
		String qquantity = quanity.getText();
        String[] quantitty = qquantity.split("x", 2);
        int a = Integer.parseInt(quantitty[0].replaceAll("[^a-zA-Z0-9_-]", ""));
        float productPrice = a*prodPrice;
        System.out.println("Price of Product"+prod+" is "+productPrice);
		return productPrice;
		

		
	}

}
