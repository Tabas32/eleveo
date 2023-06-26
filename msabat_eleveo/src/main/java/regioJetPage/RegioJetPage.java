package regioJetPage;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegioJetPage {

    private final By ORIGIN_CITY_INPUT_LOCATOR = By.id("react-select-2-input");
    private final By DESTINATION_CITY_INPUT_LOCATOR = By.id("react-select-3-input");
    private final By CITY_INPUT_OPTION_LOCATOR = By.cssSelector("div.menu-row");
    private final By DEPARTURE_DATE_BUTTON_LOCATOR = By.cssSelector("[data-id='departure-date']");
    private final By SEARCH_SUBMIT_BUTTON = By.cssSelector("[data-id='search-btn']");
    private Function<String, By> DEPARTURE_DATE_CALENDAR_DAY_BUTTON_LOCATOR = date -> By.cssSelector("[aria-label='Select departure date: " + date + "'], [aria-label='Selected departure date: " + date + "']");

    /**
     * Open regiojet.com and wait for page to load
     */
    public RegioJetPage open() {
        Configuration.pageLoadStrategy = "none";

        Selenide.open("https://www.regiojet.com/");
        localStorage().setItem("cookiesPreferences", "{\"required\":true,\"performance\":false,\"marketing\":false,\"functional\":false}");

        Wait().until(webDriver -> executeJavaScript("return document.readyState").equals("complete"));
        return this;
    }

    /**
     * @param city [String] inserted to origin city input element
     */
    public RegioJetPage setOriginCity(String city) {
        return setCity(city, ORIGIN_CITY_INPUT_LOCATOR);
    }

    /**
     * @param city [String] inserted to destination city input element
     */
    public RegioJetPage setDestinationCity(String city) {
        return setCity(city, DESTINATION_CITY_INPUT_LOCATOR);
    }

    /**
     * Set departure date to next monday
     */
    public RegioJetPage setDepartureDateToNextMonday() {
        element(DEPARTURE_DATE_BUTTON_LOCATOR).click();
        element(DEPARTURE_DATE_CALENDAR_DAY_BUTTON_LOCATOR.apply(getNextMondayDay())).click();
        return this;
    }

    /**
     * Submit search
     */
    public RegioJetPage search() {
        element(SEARCH_SUBMIT_BUTTON).click();
        return this;
    }

    private RegioJetPage setCity(String city, By inputLocator) {
        element(inputLocator).shouldBe(visible).setValue(city);
        element(CITY_INPUT_OPTION_LOCATOR).click();
        return this;
    }

    /**
     * @return date of next monday (or today date, if it is monday) in format EEEE, MMMM d, yyyy
     */
    static private String getNextMondayDay() {
        LocalDate currentDate = LocalDate.now();
        LocalDate thisWeekMonday = currentDate.with(DayOfWeek.MONDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);

        if (currentDate.getDayOfWeek().getValue() == 1) {
            return thisWeekMonday.format(formatter);
        } else {
            return  thisWeekMonday.plusWeeks(1).format(formatter);
        }
    }

    public static void main(String[] args) {
        new RegioJetPage().open()
                .setOriginCity("Ostrava")
                .setDestinationCity("Brno")
                .setDepartureDateToNextMonday()
                .search();

        try { Thread.sleep(20000); }
        catch (InterruptedException e) { System.out.println("Failed wait"); }
    }
}
