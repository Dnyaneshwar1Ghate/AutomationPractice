package AutomationPractice;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Temp.BaseClass;

public class AllTestCases extends BaseClass {

	
	@BeforeMethod
	public void setUrl()
	{
		getDriver().get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	@Test
	public void radioDemo() {
		
		List<WebElement> allRadio = getDriver().findElements(By.className("radioButton"));
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

		WebElement input = getDriver().findElement(By.id("autocomplete"));

		input.sendKeys("ind");

		Thread.sleep(2000);

		List<WebElement> suggestions = getDriver().findElements(By.xpath("//li[@class='ui-menu-item']/div"));

		for (WebElement option : suggestions) {
			if (option.getText().equalsIgnoreCase("India")) {
				option.click();
				break;
			}
		}

	}

	@Test
	public void dropDown() throws InterruptedException {

		WebElement dropdown = getDriver().findElement(By.id("dropdown-class-example"));
		Select select = new Select(dropdown);

		select.selectByVisibleText("Option1");
		Thread.sleep(2000);
		select.selectByValue("option3");
		Thread.sleep(2000);
		select.selectByIndex(2);

	}

	@Test
	public void checkBox() {

		List<WebElement> allcheck = getDriver().findElements(By.xpath("//input[@type='checkbox']"));
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
		WebElement openWindow = getDriver().findElement(By.id("openwindow"));
		openWindow.click();
		String mainWindow = getDriver().getWindowHandle();
		Set<String> allWindow = getDriver().getWindowHandles();

		for (String child : allWindow) {
			if (!mainWindow.equals(child)) {
				getDriver().switchTo().window(child);
				WebElement logo = getDriver().findElement(By.xpath("(//div[@class='logo'])[1]"));
				Assert.assertTrue(logo.isDisplayed(), "not displayed");

			}
			getDriver().switchTo().window(mainWindow);
			WebElement mainWindowLogo = getDriver().findElement(By.xpath("//img[@class='logoClass']"));
			Assert.assertTrue(mainWindowLogo.isDisplayed(), "not displayed");

		}

	}

	// @Test
	public void tabDemo() throws InterruptedException {

		getDriver().findElement(By.id("opentab")).click();

		String mainTab = getDriver().getWindowHandle();
		Set<String> allTabes = getDriver().getWindowHandles();

		for (String tab : allTabes) {
			if (!mainTab.equals(tab)) {
				getDriver().switchTo().window(tab);
			}
		}

		WebElement logo = getDriver().findElement(By.xpath("(//a[text()='Courses'])[1]"));
		Assert.assertTrue(logo.isDisplayed(), "not displayed");
		logo.click();
		Thread.sleep(3000);
		List<WebElement> para = getDriver().findElements(By.xpath("//div[@data-purpose='instructor-description']/p"));
		System.out.println(para.size());

		Assert.assertTrue(logo.isDisplayed());

	}

	@Test
	public void switchDemo() {
		WebElement alt = getDriver().findElement(By.id("alertbtn"));
		alt.click();
		Alert alert = getDriver().switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.accept();
	}

}
