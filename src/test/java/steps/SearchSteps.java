package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BookingResultPage;
import pages.BookingSearchPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.IsIterableContaining.hasItem;
import static org.testng.Assert.assertEquals;

public class SearchSteps {
    private static final String BOOKING_URL = "https://www.booking.com/searchresults.en-gb.html";
    private WebDriver driver;
    private String hotelName;
    private BookingSearchPage searchPage;
    private BookingResultPage resultPage;


    @Before
    public void init() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("user is on a search page")
    public void userIsOnASearchPage() {
        driver.get(BOOKING_URL);
    }

    @When("user is searching for hotel {string}")
    public void userIsSearchingForHotel(String searchString) {

        searchPage = new BookingSearchPage(driver);
        searchPage.inputHotelName(searchString);
        resultPage = new BookingResultPage(driver);
    }

    @Then("{string} exists on the result page")
    public void existsOnTheResultPage(String hotelName) {
        this.hotelName = hotelName;
        assertThat(String.format("There are no results for search string '%s' on first search page", hotelName),
                resultPage.getResultList(),
                hasItem(hotelName));

    }

    @And("{string} rating is {string}")
    public void hotelSRatingIs(String hotelName, String actualRating) {
        String rating = resultPage.getHotelsRating(hotelName);
        assertEquals(rating, actualRating);
    }

    @After
    public void turnDown() {
        driver.quit();
    }

}
