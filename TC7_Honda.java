package testcases;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC7_Honda {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeDriver driver;

		// 1) Go to https://www.honda2wheelersindia.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByClassName("close").click();

		// 2) Click on scooters and click dio

		driver.findElementById("link_Scooter").click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//img[@src='/assets/images/thumb/dioBS6-icon.png']")));
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();

		// 3) Click on Specifications and mouseover on ENGINE

		driver.findElementByLinkText("Specifications").click();
		Actions builder = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ENGINE")));
		WebElement engine = driver.findElementByLinkText("ENGINE");
		builder.moveToElement(engine).perform();

		// 4) Get Displacement value

		String displacementvalue = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span")
				.getText();
		String[] disval = displacementvalue.split("cc", 2);
		Thread.sleep(2000);
		Float displacementval = Float.parseFloat(disval[0].replaceAll("//s", ""));
		System.out.println("Displacement Value of Dio is " + displacementval);

		// 5) Go to Scooters and click Activa 125

		Thread.sleep(2000);
		driver.findElementById("link_Scooter").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();

		// 6) Click on Specifications and mouseover on ENGINE

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Specifications")));
		driver.findElementByLinkText("Specifications").click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ENGINE")));
		WebElement engines = driver.findElementByLinkText("ENGINE");
		builder.moveToElement(engines).perform();

		// 7) Get Displacement value

		String displacementvalue125 = driver.findElementByXPath("//span[text()='Displacement']/following-sibling::span")
				.getText();
		String[] disvall = displacementvalue125.split("cc", 2);
		Float displacementvall = Float.parseFloat(disvall[0].replaceAll("//s", ""));
		System.out.println("Displacement Value of Activa125 is " + displacementvall);

		// 8) Compare Displacement of Dio and Activa 125 and print the Scooter name
		// having better Displacement.

		if (displacementval > displacementvall) {
			System.out.println("Displacement Value of Dio is greater");
		} else {
			System.out.println("Displacement Value of Activa125 is greater");

		}

		// 9) Click FAQ from Menu

		driver.findElementByXPath("(//a[@href='/FAQ'])[1]").click();

		// 10) Click Activa 125 BS-VI under Browse By Product

		if (displacementval > displacementvall) {
			driver.findElementByLinkText("Dio BS-VI").click();
		} else {
			driver.findElementByLinkText("Activa 125 BS-VI").click();

		}

		// 11) Click Vehicle Price

		driver.findElementByXPath("//a[@href='#6a']").click();

		// 12) Make sure Activa 125 BS-VI selected and click submit

		WebElement dd = driver.findElementById("ModelID6");
		Select ddp = new Select(dd);
		String ddvalue = ddp.getFirstSelectedOption().getText();
		if (ddvalue.contains("Activa 125 BS-VI")) {
			System.out.println("Selected value is 'Activa 125 BS-VI'");
			driver.findElementById("submit6").click();

		} else {
			System.out.println("Selected value is not 'Activa 125 BS-VI'");

		}

		// 13) click the price link

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.linkText("Click here to know the price of Activa 125 BS-VI.")));
		driver.findElementByLinkText("Click here to know the price of Activa 125 BS-VI.").click();

		// 14) Go to the new Window and select the state as Tamil Nadu and city as
		// Chennai

		Set<String> window = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(window);
		driver.switchTo().window(ls.get(1));

		WebElement state = driver.findElementById("StateID");
		Select stateobj = new Select(state);
		stateobj.selectByVisibleText("Tamil Nadu");

		WebElement city = driver.findElementById("CityID");
		Select cityobj = new Select(city);
		cityobj.selectByVisibleText("Chennai");

		// 15) Click Search

		driver.findElementByXPath("//button[text()='Search']").click();

		// 16) Print all the 3 models and their prices

		WebElement table = driver.findElementById("gvshow");

		List<WebElement> model = table.findElements(By.xpath("//tbody[@style='background-color:white']//tr"));

		for (WebElement columns : model) {
			List<WebElement> cols = columns.findElements(By.tagName("td"));

			for (WebElement col : cols) {

				if (col.getText().contains("Chennai")) {
					System.out.println();
				} else {
					System.out.print(col.getText() + "\t");
				}

			}
			System.out.println();

		}

		// 17) Close the Browser

		driver.quit();

	}

}
