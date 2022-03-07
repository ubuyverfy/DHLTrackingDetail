package tracking.detail;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HitUrl {

	public static WebDriver driver;
	public int pdf = 1;
	//@Test(priority = 1, enabled = true, invocationCount = 1)
	public void lunchBrowser() throws InterruptedException, AWTException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-blink-features=AutomationControlled");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("Chrome lunched");
		driver.get("https://www.dhl.com/in-en/home/tracking/tracking-express.html?submit=1&tracking-id=6866798934");
		Thread.sleep(8000);
		driver.findElement(By.xpath("//button[@id='accept-recommended-btn-handler']")).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//button[@id='c-tracking-result--moredetails-dropdown-button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='c-tracking-result--checkpoints-dropdown-button']")).click();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-250)", "");
		
		driver.findElement(By.xpath("//button[@title='Print']")).click();
		Thread.sleep(3000);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);

		File source = new File("/home/ramesh/Downloads");
		File f1 = new File("/home/ramesh/TackingData" + "/PDF" + pdf);
		f1.mkdir();
		String path = f1.getAbsolutePath();
		System.out.println(path);
		File dest = new File(path);
		try {
			FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pdf++;
	}

}
