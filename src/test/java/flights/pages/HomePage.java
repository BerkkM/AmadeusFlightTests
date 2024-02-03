package flights.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import flights.methods.Methods;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static flights.driver.Base.driver;

public class HomePage {

    Methods methods;
    Logger logger = LogManager.getLogger(Methods.class);

    public HomePage() {
        methods = new Methods();
    }
    private final String firstCityInputId = "headlessui-combobox-input-:Rq9lla:";
    private final String secondCityInputId = "headlessui-combobox-input-:Rqhlla:";

    public void performBugCheck(String fromName, String toName) {
        methods.enterCity(fromName, firstCityInputId);
        methods.enterCity(toName, secondCityInputId);

        String pageSource = driver.getPageSource();
        if (fromName.equals(toName)) {
            logger.info("Bug tespit edildi. Aynı şehir iki kez giriş yapılabiliyor.");
        } else if (!fromName.equals(toName)) {
            logger.info("Farklı şehir girildi ve " + getExpectedItemCount(pageSource) + " uçuş bulundu.");
            checkFlightItems(pageSource);
        }

    }

    private void checkFlightItems(String pageSource) {
        if (pageSource.contains("class=\"mb-10\"")) {
            int expectedItemCount = getExpectedItemCount(pageSource);
            if (expectedItemCount > 0) {
                List<WebElement> flightItems = driver.findElements(By.cssSelector(".overflow-hidden.rounded-xl.border.border-gray-200"));
                if (flightItems.size() == expectedItemCount) {
                    logger.info("Uçuşlar başarıyla bulundu. Toplam " + expectedItemCount + " uçuş bulunuyor.");
                } else {
                    logger.error("Hata: Gerçek uçuş sayısı ile beklenen uçuş sayısı uyuşmuyor.");
                    Assert.fail("Uçuş sayısı uyuşmuyor.");
                }
            } else {
                logger.error("Hata: Beklenen uçuş sayısı sıfır veya daha küçük.");
                Assert.fail("Beklenen uçuş sayısı sıfır veya daha küçük.");
            }
        } else {
            logger.error("Hata: Uçuşlar bulunamadı.");
            Assert.fail("Uçuşlar bulunamadı.");
        }
    }

    private int getExpectedItemCount(String pageSource) {
        int expectedItemCount = 0;
        Pattern pattern = Pattern.compile("Found (\\d+) items");
        Matcher matcher = pattern.matcher(pageSource);
        if (matcher.find()) {
            expectedItemCount = Integer.parseInt(matcher.group(1));
        }
        return expectedItemCount;
    }
}





