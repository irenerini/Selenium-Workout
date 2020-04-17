package testcases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class TC3MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Click Hotels, Enter city as Goa, and choose Goa, India

		driver.findElementByXPath("//li[@class='menu_Hotels']//a[1]").click();
		driver.findElementById("city").sendKeys("Goa", Keys.TAB);
		Thread.sleep(2000);

		// Enter Check in date as Next month 15th (May 15) and Check out as start date+5

		WebElement datebt = driver.findElementByXPath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']");
		do {
			datebt.click();
		} while (driver.findElementByXPath("//div/div[text()='June']").isSelected());

		String month = "June";
		driver.findElementByXPath("//div/div[text()='" + month + "']/following::div[text()='15']").click();
		driver.findElementByXPath("//div/div[text()='" + month + "']/following::div[text()='19']").click();

		// Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click
		// Apply Button, Search button

		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[@data-cy='adults-2']").click();
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		WebElement age = driver.findElementByXPath("//select[@id='0']");
		age.click();
		Select opts = new Select(age);
		opts.selectByVisibleText("12");
		driver.findElementByXPath("//button[@class='primaryBtn btnApply']").click();
		driver.findElementById("hsw_search_button").click();
		Thread.sleep(2000);
		
		// Select locality as Baga

		driver.get(driver.getCurrentUrl());
		driver.navigate().to(driver.getCurrentUrl());
		driver.findElementByXPath("//label[text()='Baga']").click();
		Thread.sleep(2000);
		
		// Select 5 start in Star Category under Select Filters

		driver.findElementByXPath("(//label[text()='5 Star'])[1]").click();

		// Click on the first resulting hotel and go to the new window

		driver.findElementByXPath("(//div[contains(@class,'listingRow ')]//div)[1]").click();

		// 10) Print the Hotel Name

		Set<String> window = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(window);
		driver.switchTo().window(ls.get(1));
		WebElement hname = driver.findElementById("detpg_hotel_name");
		System.out.println("Hotel Name is " + hname.getText());

		// 11) Click MORE OPTIONS link and Select 3Months plan and close

		driver.findElementByXPath("(//span[text()='MORE OPTIONS'])[1]").click();
		driver.findElementByXPath("(//td[@class='textRight']//span)[2]").click();
		driver.findElementByClassName("close").click();
		Thread.sleep(1000);

		// 12) Click on BOOK THIS NOW

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1500)", "");
		Thread.sleep(1000);
		driver.findElementById("detpg_book_combo_btn").click();

		// 13) Print the Total Payable amount

		WebElement tamt = driver.findElementById("revpg_total_payable_amt");
		System.out.println("Total Payabale Amount is " + tamt.getText());

		// 14) Close the browser

		driver.quit();

	}

}
