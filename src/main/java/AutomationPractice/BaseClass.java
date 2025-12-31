package AutomationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	WebDriver driver;
	@BeforeMethod
	public void setupDriver() {
		ChromeOptions options = new ChromeOptions();

		String environment = System.getenv("CI");

		if ("true".equalsIgnoreCase(environment)) {

			options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920x1080");
			options.addArguments("--no-sandbox");
		} else {

			options.addArguments("--start-maximized");
		}

		driver = new ChromeDriver(options);

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

	}


}
