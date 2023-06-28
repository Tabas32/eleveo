package regioJetPage;

import com.codeborne.selenide.*;
import models.ConnectionTransfer;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static utils.DateUtility.getNextMondayDay;

public class RegioJetPage {

    private final String DATE_FORMAT = "EEEE, MMMM d, yyyy";

    private final By ORIGIN_CITY_INPUT_LOCATOR = By.id("react-select-2-input");
    private final By DESTINATION_CITY_INPUT_LOCATOR = By.id("react-select-3-input");
    private final By CITY_INPUT_OPTION_LOCATOR = By.cssSelector("div.menu-row");
    private final By DEPARTURE_DATE_BUTTON_LOCATOR = By.cssSelector("[data-id='departure-date']");
    private final By SEARCH_SUBMIT_BUTTON = By.cssSelector("[data-id='search-btn']");
    private final By CONNECTION_TIME = By.cssSelector("h2[aria-label^='Connection ']"); // todo probably remove
    private final By CONNECTION_CARD = By.cssSelector("li div div.card");

    private Function<String, By> DEPARTURE_DATE_CALENDAR_DAY_BUTTON_LOCATOR = date -> By.cssSelector("[aria-label='Select departure date: " + date + "'], [aria-label='Selected departure date: " + date + "']");
    private Function<String, By> PARAGRAPH_WITH_TEXT = text -> By.xpath("//p[contains(text(), '" + text + "')]");

    // connection card locators
    private final By CONNECTION_CARD_EXPAND_BUTTON = By.cssSelector("div > svg");
    private final By CONNECTION_CARD_ORIGIN_CITY = By.cssSelector("div.cardOpenTransfer-depart-icon + div span:first-of-type");
    private final By CONNECTION_CARD_DESTINATION_CITY = By.cssSelector("div.cardOpenTransfer-arrival-icon + div");
    private final By CONNECTION_CARD_JOURNEY = By.cssSelector("ul[aria-label='Journey information']");
    private final By CONNECTION_CARD_TRANSFER_ITEM = By.cssSelector("ul[aria-label='Journey information'] li");

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
        element(DEPARTURE_DATE_CALENDAR_DAY_BUTTON_LOCATOR.apply(getNextMondayDay(DATE_FORMAT))).click();
        return this;
    }

    /**
     * Submit search
     */
    public RegioJetPage search() {
        element(SEARCH_SUBMIT_BUTTON).click();
        return this;
    }

    public boolean isDateDisplayedOnPage(String date) {
        return element(PARAGRAPH_WITH_TEXT.apply(date)).shouldBe(visible).isDisplayed();
    }

    public List<String> getOriginCities() {
        return getInfoFromConnectionCard( e -> e.find(CONNECTION_CARD_ORIGIN_CITY).text() );
    }

    public List<String> getDestinationCities() {
        return getInfoFromConnectionCard( e -> e.find(CONNECTION_CARD_DESTINATION_CITY).text() );
    }

    public List<String> getTransferLabels() {
        return getInfoFromConnectionCard( e -> e.find(CONNECTION_CARD_EXPAND_BUTTON).parent().sibling(1).text());
    }

    public List<List<ConnectionTransfer>> getTransfers() {
        return getInfoFromConnectionCard( e -> {
            e.find(CONNECTION_CARD_TRANSFER_ITEM).shouldBe(visible);
            return e.findAll(CONNECTION_CARD_TRANSFER_ITEM).stream().map(transferItem -> {
                        ElementsCollection transferData = transferItem.findAll("div[aria-hidden='true']");
                        if (transferData.isEmpty()) { return null; }

                        return new ConnectionTransfer(
                                transferData.get(1).text(),
                                transferData.get(4).text(),
                                transferData.get(0).text(),
                                transferData.get(3).text(),
                                transferData.get(2).text()
                        );
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        });
    }

    private RegioJetPage setCity(String city, By inputLocator) {
        element(inputLocator).shouldBe(visible).setValue(city);
        element(CITY_INPUT_OPTION_LOCATOR).click();
        return this;
    }

    private <T> List<T> getInfoFromConnectionCard(Function<SelenideElement, T> actionOnConnectionCard) {
        element(CONNECTION_CARD).shouldBe(visible);

        return elements(CONNECTION_CARD).stream().map( connectionCard -> {
            if (!connectionCard.find(CONNECTION_CARD_JOURNEY).isDisplayed()) {
                connectionCard.find(CONNECTION_CARD_EXPAND_BUTTON).click();
            }

            return actionOnConnectionCard.apply(connectionCard);
        })
            .collect(Collectors.toList());
    }
}
