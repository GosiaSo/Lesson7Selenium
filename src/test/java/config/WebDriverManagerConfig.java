import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Level;

public class WebDriverManagerConfig {

    static WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = setDriver("chrome");
    }

    static WebDriver setDriver(String browser) {
        WebDriverManager.chromedriver().setup();

        switch (browser) {
            case "firefox" -> {
                return driver = new FirefoxDriver();
            }
            case "edge" -> {
                return driver = new EdgeDriver();
            }
            default -> {
                driver = new ChromeDriver();
                java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                return driver;
            }
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
