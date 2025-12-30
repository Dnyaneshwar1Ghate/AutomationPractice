package AutomationPractice;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AllTestCases {

	WebDriver driver;

	@BeforeMethod
	public void setupDriver() {
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();

	}

	 @Test
	public void radioDemo() {
		List<WebElement> allRadio = driver.findElements(By.className("radioButton"));
		System.out.println(allRadio.size());

		for (WebElement radio : allRadio) {
			String value = radio.getAttribute("value");
			System.out.println(value);

			if (value.equalsIgnoreCase("radio2")) {
				radio.click();
				break;
			}
		}
	}

	 @Test
	public void suggessionDemo() throws InterruptedException {

		WebElement input = driver.findElement(By.id("autocomplete"));

		input.sendKeys("ind");

		Thread.sleep(2000);

		List<WebElement> suggestions = driver.findElements(By.xpath("//li[@class='ui-menu-item']/div"));

		for (WebElement option : suggestions) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}

	}

	 @Test
	public void dropDown() throws InterruptedException {

		WebElement dropdown = driver.findElement(By.id("dropdown-class-example"));
		Select select = new Select(dropdown);

		select.selectByVisibleText("Option1");
		Thread.sleep(2000);
		select.selectByValue("option3");
		Thread.sleep(2000);
		select.selectByIndex(2);

	}

	 @Test
	public void checkBox() {

		List<WebElement> allcheck = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(allcheck.size());

		for (WebElement all : allcheck) {
			String value = all.getAttribute("value");
			if (value.equalsIgnoreCase("option1")) {
				all.click();
				break;
			}
		}

	}

	@Test
	public void switchWindow() {
		WebElement openWindow = driver.findElement(By.id("openwindow"));
		openWindow.click();
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindow = driver.getWindowHandles();

		for (String child : allWindow) {
			if (!mainWindow.equals(child)) {
				driver.switchTo().window(child);
				WebElement logo = driver.findElement(By.xpath("(//div[@class='logo'])[1]"));
				Assert.assertTrue(logo.isDisplayed(), "not displayed");

			}
			driver.switchTo().window(mainWindow);
			WebElement mainWindowLogo = driver.findElement(By.xpath("//img[@class='logoClass']"));
			Assert.assertTrue(mainWindowLogo.isDisplayed(), "not displayed");

		}

	}

	@Test
	public void tabDemo() throws InterruptedException {

		driver.findElement(By.id("opentab")).click();

		String mainTab = driver.getWindowHandle();
		Set<String> allTabes = driver.getWindowHandles();

		for (String tab : allTabes) {
			if (!mainTab.equals(tab)) {
				driver.switchTo().window(tab);
			}
		}

		WebElement logo = driver.findElement(By.xpath("(//ul[@class='navbar-nav ml-auto']/li)[2]"));
		Assert.assertTrue(logo.isDisplayed(), "not displayed");
		logo.click();
		Thread.sleep(3000);
		List<WebElement> para = driver.findElements(By.xpath("//div[@data-purpose='instructor-description']/p"));
		System.out.println(para.size());

		Assert.assertTrue(logo.isDisplayed());

	}

	@Test
	public void switchDemo()
	{
		 WebElement alt = driver.findElement(By.id("alertbtn"));
		 alt.click(); 
		 Alert alert = driver.switchTo().alert(); 
		 String alertText = alert.getText();
		 System.out.println(alertText);
		 alert.accept();	 
	}
	
	@AfterMethod
	public void tearDown() {
		 driver.quit();
	}
}
