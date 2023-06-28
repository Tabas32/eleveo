package steps;

import models.ConnectionTransfer;
import models.SearchDetails;
import org.assertj.core.api.SoftAssertions;
import regioJetPage.RegioJetPage;
import utils.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.DateUtility.getNextMondayDay;

/**
 * Steps related to regiojet page
 */
public class RegioJetSteps {

    private final String DATE_FORMAT = "EEEE, MMMM d, yyyy";

    // Stores
    private SearchDetails searchDetails = new SearchDetails();

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
        searchDetails.setDepartureDate(getNextMondayDay(DATE_FORMAT));
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

    public void printInformationAboutRoutes() {
        List<List<ConnectionTransfer>> allConnections = regioJetPage.getTransfers();

        allConnections.stream().forEach( transfers -> {
            if (transfers.isEmpty()) { return; }

            System.out.println("\n===========================================");
            System.out.println("Departure time: " + transfers.get(0).getDepartureTime());
            System.out.println("Arrival time: " + transfers.get(transfers.size() - 1).getArrivalTime());
            System.out.println("Transfers: " + (transfers.size() - 1));
            System.out.println("Travel time (in train): " + sumOfTimes(transfers.stream().map(c -> c.getLength()).collect(Collectors.toList())));
        });
    }

    private void validateConnectionCardCities(List<String> cities, String expectedCity) {
        SoftAssertions softly = new SoftAssertions();
        cities.stream().forEach( city -> softly.assertThat(city).startsWith(expectedCity));
        softly.assertAll();
    }

    private String sumOfTimes(List<String> times) {
        int totalHours = 0;
        int totalMinutes = 0;

        for (String time : times) {
            String[] parts = getOnlyTime(time).split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);

            totalHours += hours;
            totalMinutes += minutes;
        }

        totalHours += totalMinutes / 60;
        totalMinutes %= 60;

        return String.format("%02d:%02d", totalHours, totalMinutes);
    }

    private String getOnlyTime(String str) {
        String timeRegex = "\\d{2}:\\d{2}";

        Pattern pattern = Pattern.compile(timeRegex);
        Matcher matcher = pattern.matcher(str);

        matcher.find();
        return matcher.group();
    }
}
