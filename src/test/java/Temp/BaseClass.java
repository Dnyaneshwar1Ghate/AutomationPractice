package Temp;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    // Thread-safe WebDriver for parallel execution
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driverThread.get();
    }

    @BeforeClass
    public void setup() {

        
        boolean isJenkins = System.getenv("JENKINS_HOME") != null;

        
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (isJenkins) {
            // ---------------------------
            // JENKINS HEADLESS CONFIG
            // ---------------------------
            options.addArguments("--headless=new"); // Use "--headless" for older Chrome
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");

            System.out.println("Running in Jenkins → Headless Chrome enabled");
        } else {
            // ---------------------------
            // LOCAL EXECUTION
            // ---------------------------
            options.addArguments("--start-maximized");
            System.out.println("Running locally → Normal Chrome");
        }

        try {
            WebDriver driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driverThread.set(driver);
        } catch (Exception e) {
            System.out.println("ChromeDriver launch failed: " + e.getMessage());
            throw e;
        }
    }

  // @AfterClass
    public void tearDown() {
        WebDriver driver = driverThread.get();
        
        if (driver != null) {
            try {
                driver.quit();
                driverThread.remove();
            } catch (Exception e) {
                System.out.println("Error during driver.quit(): " + e.getMessage());
            }
        }
    }

}