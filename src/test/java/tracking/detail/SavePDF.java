package tracking.detail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SavePDF {

	public static WebDriver driver;
	public int count = 1;
	
	@BeforeTest
	public void lunchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-blink-features=AutomationControlled");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("Chrome lunched");
		
	}

	@Test(priority = 1, enabled = true, invocationCount = 5)
	public void savePDF() throws InterruptedException, AWTException, IOException, DocumentException {

		
		driver.get("https://www.dhl.com/in-en/home/tracking/tracking-express.html?submit=1&tracking-id=6866798934");
		Thread.sleep(6000);
		if(count==1) {
		
		driver.findElement(By.xpath("//button[@id='accept-recommended-btn-handler']")).click();
		Thread.sleep(3000);
		}

		driver.findElement(By.xpath("//button[@id='c-tracking-result--moredetails-dropdown-button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='c-tracking-result--checkpoints-dropdown-button']")).click();
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)", "");

		Thread.sleep(2000);
		WebElement textbox = driver.findElement(
				By.xpath("//input[@class='c-tracking-bar--input js--tracking--input-field l-grid--w-auto-s']"));
		textbox.clear();
		Thread.sleep(2000);
		textbox.sendKeys("TrackingDetail" + count);
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
		builder.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();

		/*
		 * //Scanning text
		 * 
		 * String data =driver.findElement(By.
		 * xpath("//div[@class='c-tracking-result--detail l-grid l-grid--w-100pc-s l-grid--p-2u-m ']"
		 * )).getText(); System.out.println(data);
		 * 
		 * Document document = new Document(); PdfWriter writer =
		 * PdfWriter.getInstance(document, new
		 * FileOutputStream("/home/ramesh/TackingData/HelloWorld.pdf"));
		 * document.open(); document.add(new Paragraph(data)); document.close();
		 * writer.close();
		 */

		driver.findElement(By.xpath("//button[@title='Print']")).click();
		Thread.sleep(3000);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

		System.out.println("total pdf saved :"+count);
		count++;

	}
}
