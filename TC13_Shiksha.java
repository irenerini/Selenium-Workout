package testcases;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC13_Shiksha {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 1) Go to https://studyabroad.shiksha.com/

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-Notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_81.exe");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2) Mouse over on Colleges and click MS in Computer Science &Engg under MS
		// Colleges

		WebElement college = driver.findElementByCssSelector("div#mypanel>div>ul>li:nth-of-type(3)>label");
		Actions builder = new Actions(driver);
		builder.moveToElement(college).perform();
		WebElement course = driver.findElementByLinkText("MS in Computer Science &Engg");
		builder.moveToElement(course).click().perform();
		Thread.sleep(2000);

		// 3) Select GRE under Exam Accepted and Score 300 & Below

		driver.findElementByXPath("//div[@class='tar']//a").click();
		driver.findElementByXPath("//p[text()='GRE']/preceding-sibling::span").click();
		Thread.sleep(2000);
		WebElement score = driver.findElementByXPath("(//select[@name='examsScore[]'])[1]");
		Select sr = new Select(score);
		score.click();
		sr.selectByVisibleText("300 & below");

		// 4) Max 10 Lakhs under 1st year Total fees, USA under countries

		Thread.sleep(2000);
		driver.findElementByXPath("//p[text()='Max 10 Lakhs']").click();
		Thread.sleep(2000);
		driver.findElementByCssSelector(
				"div#locationFilterScrollbar>div:nth-of-type(2)>div>ul>li:nth-of-type(4)>label>span").click();

		// 5) Select Sort By: Low to high 1st year total fees

		Thread.sleep(3000);
		WebElement sort = driver.findElementById("categorySorter");
		Select srt = new Select(sort);
		sort.click();
		srt.selectByVisibleText("Low to high 1st year total fees");
		Thread.sleep(2000);

		// 6) Click Add to compare of the College having least fees with Public
		// University, Scholarship and Accomadation

		List<WebElement> fees = driver.findElementsByXPath(
				"//div[@class='detail-col flLt' and (@style='width: 170px;' or @style='width:170px;')]//p");

		List<WebElement> nocolleges = driver.findElementsByXPath("(//li[@class='clearwidth '])");

		List<Float> colflist = new LinkedList<Float>();

		for (int j = 1; j <= nocolleges.size(); j++) {

			List<WebElement> facilities = driver
					.findElementsByXPath("(//li[@class='clearwidth '])[" + j + "]//span[@class='tick-mark']");

			if (facilities.size() == 3) {

				float collfee = Float.parseFloat(fees.get(j - 1).getText().replaceAll("[^0-9.]", ""));
				colflist.add(collfee);

			}

		}

		Collections.sort(colflist);
		
		WebElement colege = driver.findElementByXPath("//p[contains(text(),'" + colflist.get(0)
				+ "')]/ancestor::div[@class='clearwidth']/following-sibling::div//p[text()='Add to compare']");
		colege.click();
		
		System.out.println("Clicked on college which has all three facilities with lowest fees");
		Thread.sleep(2000);

		// 7) Select the first college under Compare with similar colleges

		driver.findElementByXPath("(//a[@class='add-tag-title'])[1]").click();

		// 8) Click on Compare College>

		driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();

		// 9) Select When to Study as 2021

		driver.findElementByXPath("//strong[text()='2021']").click();
		Thread.sleep(2000);
		// 10) Select Preferred Countries as USA

		driver.findElementByXPath("//div[text()='Preferred Countries']/following-sibling::div").click();
		Thread.sleep(2000);
		// driver.findElementByXPath("//label[@for='USA_dWtusL']").click();
		driver.findElementByLinkText("ok").click();
		Thread.sleep(2000);

		// 11) Select Level of Study as Masters

		driver.findElementByXPath("//strong[text()='Masters']").click();

		// 12) Select Preferred Course as MS

		driver.findElementByXPath("//div[text()='Preferred Course']/following-sibling::div").click();
		driver.findElementByXPath("//li[text()='MS']").click();
		Thread.sleep(2000);

		// 13) Select Specialization as "Computer Science & Engineering"

		driver.findElementByXPath("//div[text()='Preferred Specialisations']/following-sibling::div").click();
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
		Thread.sleep(2000);

		// 14) Click on Sign Up

		driver.findElementById("signup").click();

		// 15) Print all the warning messages displayed on the screen for missed
		// mandatory fields

		List<WebElement> helptext = driver.findElementsByXPath("//div[@class='helper-text']");

		System.out.println("********Encountered following error messages**********" + "\n");

		for (WebElement htext : helptext) {

			if (htext.isDisplayed()) {

				System.out.print(htext.getText() + "\n");

			}

		}

		driver.close();

	}

}
