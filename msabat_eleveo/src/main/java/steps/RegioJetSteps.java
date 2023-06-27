package steps;

import models.SearchDetails;
import org.assertj.core.api.SoftAssertions;
import regioJetPage.RegioJetPage;
import utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.DateUtility.getNextMondayDay;

/**
 * Steps related to regiojet page
 */
public class RegioJetSteps {

    // Stores
    private SearchDetails searchDetails = new SearchDetails();
    //private List<RouteConnection> routeConnections = new ArrayList(); todo remove

    // Page objects
    private RegioJetPage regioJetPage = new RegioJetPage();

    public void userOpensRegioJetPage() {
        regioJetPage.open();
    }

    public void userSearchForConnectionForNextMonday(String originCity, String destinationCity) {
        regioJetPage.setOriginCity(originCity)
                .setDestinationCity(destinationCity)
                .setDepartureDateToNextMonday()
                .search();

        searchDetails.setOriginCity(originCity);
        searchDetails.setDestinationCity(destinationCity);
        searchDetails.setDepartureDate(getNextMondayDay());
    }

    public void  connectionsAreDisplayedForTheSpecifiedDay() {
        assertThat(regioJetPage.isDateDisplayedOnPage(searchDetails.getDepartureDate()))
                .withFailMessage(searchDetails.getDepartureDate() + " should be displayed")
                .isTrue();
    }

    public void allConnectionDepartureFromSpecifiedCity() {
        validateConnectionCardCities(regioJetPage.getOriginCities(), searchDetails.getOriginCity());
    }

    public void allConnectionArriveToSpecifiedCity() {
        validateConnectionCardCities(regioJetPage.getDestinationCities(), searchDetails.getDestinationCity());
    }

    public void allConnectionsHaveCorrectNumberOfTransfersDisplayed() {
        List<Integer> numbersOfTransferFromLabels = regioJetPage.getTransferLabels().stream()
                .map(StringUtils::parseFirstNumber).collect(Collectors.toList());

        List<Integer> numbersOfTransferFromTransfers = regioJetPage.getTransfers().stream()
                .map( connectionTransfers -> connectionTransfers.size() - 1).collect(Collectors.toList());

        assertThat(numbersOfTransferFromLabels).isEqualTo(numbersOfTransferFromTransfers);
    }

    private void validateConnectionCardCities(List<String> cities, String expectedCity) {
        SoftAssertions softly = new SoftAssertions();
        cities.stream().forEach( city -> softly.assertThat(city).startsWith(expectedCity));
        softly.assertAll();
    }

    public static void main(String[] args) {
        RegioJetSteps steps = new RegioJetSteps();

        steps.userOpensRegioJetPage();
        steps.userSearchForConnectionForNextMonday("Ostrava", "Brno");
        steps.connectionsAreDisplayedForTheSpecifiedDay();

        steps.allConnectionDepartureFromSpecifiedCity();
        steps.allConnectionArriveToSpecifiedCity();
        steps.allConnectionsHaveCorrectNumberOfTransfersDisplayed();
    }

}
