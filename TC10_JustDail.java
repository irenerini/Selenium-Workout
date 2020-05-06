package testcases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC10_JustDail {

	static ChromeDriver driver;

	public static void closepp() {
		try {
			if (driver.findElementByXPath("(//section[@class='jpbg']//span[contains(@onclick,'closePopUp')])[4]")
					.isEnabled()) {

				// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@onclick='closePopUp('best_deal_div');']")));
				driver.findElementByXPath("(//section[@class='jpbg']//span[contains(@onclick,'closePopUp')])[4]")
						.click();

			}
		} catch (Exception e) {

		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		// 1) https://www.justdial.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.justdial.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2) Set the Location as Chennai

		WebElement setloc = driver.findElementById("city");
		setloc.click();
		setloc.sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElementByCssSelector("a#Chennai").click();
		Thread.sleep(2000);

		// 3) Click Auto Care in the left menu

		driver.findElementByXPath("//span[text()='Auto care']").click();

		// 4) Click Car Repair

		Thread.sleep(2000);
		driver.findElementByXPath("//span[@title='Car Repair']").click();
		Thread.sleep(2000);

		// 5) Click Car Brand as Hyundai

		driver.findElementByXPath("(//span[text()='Hyundai'])[1]").click();

		// 6) Click Make as Hyundai Xcent

		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Hyundai Xcent']")));
		driver.findElementByXPath("//span[@title='Hyundai Xcent']").click();
		Thread.sleep(2000);
		closepp();

		// 7) Click on Location and Enter Porur
		// 8) Select Porur from the dropdown list
		// 9) Select Distance starting from 1 km
		// Skipped above 3 steps due to application not stable

		Thread.sleep(2000);
		closepp();

		// 10) Identify all the Service Center having Ratings >=4.5 and Votes >=50

		List<WebElement> rates = driver.findElementsByXPath("(//span[@class='green-box'])");
		List<WebElement> votes = driver
				.findElementsByXPath("//p[@class='newrtings ']//span[@class='rt_count lng_vote']");
		List<WebElement> srnames = driver.findElementsByXPath("//span[@class='lng_cont_name']");
		System.out.println(srnames.size());

		List<String> names = new ArrayList<String>();
		List<String> phno = new ArrayList<String>();

		for (int i = 0; i < (rates.size()); i++) {

			Float rate = Float.parseFloat(rates.get(i).getText());
			int votee = Integer.parseInt(votes.get(i).getText().replaceAll("[^0-9]", ""));
			if (rate >= 4.5 && votee > 50) {

				System.out.println(srnames.get(i).getText());
				String namess = srnames.get(i).getText();
				Thread.sleep(2000);
				String phonenums = phNumbers(i + 1);
				System.out.println("\n" + phonenums);
				System.out.println();
				names.add(namess);
				phno.add(phonenums);

			}

		}

		closepp();

		// 11) Save all the Service Center name and Phone number matching the above
		// condition in excel

		File file = new File("TC10JustDail_data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("JustDail");

		for (int i = 0; i < names.size(); i++) {

			sheet.createRow(i).createCell(0).setCellValue(names.get(i));
			sheet.getRow(i).createCell(1).setCellValue(phno.get(i));

		}

		FileOutputStream files = new FileOutputStream(file);
		workbook.write(files);

		// 12) Close the browser

		driver.close();
	}

	public static String phNumbers(int phindex) {

		List<WebElement> phno = driver
				.findElementsByXPath("(//p[@class='contact-info '])[" + phindex + "]//span//span");

		char[] phnum = new char[phno.size()];

		for (int j = 0; j < phno.size(); j++) {

			// List<WebElement> phnum =
			// driver.findElementsByXPath("(//p[@class='contact-info '])[i]//span//span");

			String classname = phno.get(j).getAttribute("class");

			switch (classname) {

			case "mobilesv icon-dc":
				System.out.print("+");
				phnum[j] = '+';
				break;

			case "mobilesv icon-fe":
				System.out.print("(");
				phnum[j] = '(';
				break;

			case "mobilesv icon-hg":
				System.out.print(")");
				phnum[j] = ')';
				break;

			case "mobilesv icon-ba":
				System.out.print("-");
				phnum[j] = '-';
				break;

			case "mobilesv icon-yz":
				System.out.print("1");
				phnum[j] = '1';
				break;

			case "mobilesv icon-wx":
				System.out.print("2");
				phnum[j] = '2';
				break;

			case "mobilesv icon-vu":
				System.out.print("3");
				phnum[j] = '3';
				break;

			case "mobilesv icon-ts":
				System.out.print("4");
				phnum[j] = '4';
				break;

			case "mobilesv icon-rq":
				System.out.print("5");
				phnum[j] = '5';
				break;

			case "mobilesv icon-po":
				System.out.print("6");
				phnum[j] = '6';
				break;

			case "mobilesv icon-nm":
				System.out.print("7");
				phnum[j] = '7';
				break;

			case "mobilesv icon-lk":
				System.out.print("8");
				phnum[j] = '8';
				break;

			case "mobilesv icon-ji":
				System.out.print("9");
				phnum[j] = '9';
				break;

			case "mobilesv icon-acb":
				System.out.print("0");
				phnum[j] = '0';
				break;

			}
		}

		String phnumber = new String(phnum);
		// System.out.println("\n"+"Phone is " + phnumber);

		return phnumber;

	}

}
