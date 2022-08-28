package automationpractice;

import config.WebDriverManagerConfig;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainTest extends WebDriverManagerConfig {

    private static final String AUTOMATION_PRACTICE = "http://automationpractice.com/index.php";
    private static final String PRODUCT_PRICES_XPATH = "//ul [@id=\"homefeatured\"]//li//div[1] [@itemprop=\"offers\"]/span [@itemprop=\"price\"]";
    private final List<String> expectedPrices = new ArrayList<>(List.of("$16.51", "$27.00", "$26.00", "$50.99", "$28.98", "$30.50", "$16.40"));

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Test
    @Tag("price")
    void checkPriceShortSleeveTshirt() {
        driver.get(AUTOMATION_PRACTICE);
        log.info("Otworzono stronę: " + AUTOMATION_PRACTICE);
        driver.manage().window().maximize();

        List<WebElement> productPrices = driver.findElements(By.xpath(PRODUCT_PRICES_XPATH));
        List<String> prices = new ArrayList<>();

        for (WebElement productPrice : productPrices) {
            prices.add(productPrice.getText());
        }

        for (int i = 0; i < expectedPrices.size(); i++) {
            assertThat(prices.get(i)).isEqualTo(expectedPrices.get(i));
            log.info("Cena na stronie: " + prices.get(i) + " | Oczekiwana cena: " + expectedPrices.get(i));
        }
    }
}
