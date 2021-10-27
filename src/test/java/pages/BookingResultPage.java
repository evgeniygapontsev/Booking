package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class BookingResultPage extends BasePage {
    private List<String> hotelsList;

    public BookingResultPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getResultList() {
        return hotelsList = driver.findElements(By.cssSelector("[data-testid=title]")).stream()
                .map(result -> result.getAttribute("innerText"))
                .filter(result -> !result.isEmpty()).collect(Collectors.toList());
    }

    public String getHotelsRating(String hotelName) {
        String rating = driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]" +
                "//ancestor::*[contains(@class,'b9c27d6646')]" +
                "//descendant::*[contains(@class, 'bd528f9ea6')]", hotelName))).getText();
        return rating;
    }
}
