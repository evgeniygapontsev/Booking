package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingSearchPage extends BasePage {

    public BookingSearchPage(WebDriver driver) {
        super(driver);
    }

    public void inputHotelName(String searchString) {
        driver.findElement(By.id("ss")).click();
        driver.findElement(By.id("ss")).sendKeys(searchString);
        driver.findElement(By.cssSelector("[type=submit")).click();
    }
}
