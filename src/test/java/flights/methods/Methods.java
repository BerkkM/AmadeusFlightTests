package flights.methods;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import flights.driver.Base;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods {

    WebDriver driver;
    public Methods() {
        driver = Base.driver;
    }

    public WebElement findElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void enterCity(String city, String inputId) {
        WebElement cityInput = findElement(By.id(inputId));
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
    }

}
