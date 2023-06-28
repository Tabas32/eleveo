import org.junit.jupiter.api.Test;
import steps.RegioJetApiSteps;
import steps.RegioJetSteps;

public class RegioJetSearch {

    @Test
    public void userIsAbleToSearchForRouteConnection() {
        RegioJetSteps steps = new RegioJetSteps();

        steps.userOpensRegioJetPage();
        steps.userSearchForConnectionForNextMonday("Ostrava", "Brno");
        steps.connectionsAreDisplayedForTheSpecifiedDay();

        steps.allConnectionDepartureFromSpecifiedCity();
        steps.allConnectionArriveToSpecifiedCity();
        steps.allConnectionsHaveCorrectNumberOfTransfersDisplayed();
    }

    @Test
    public void obtainData() {
        System.out.println("Printing data obtained with selenide from GUI");
        System.out.println("=============================================");
        RegioJetSteps steps = new RegioJetSteps();

        steps.userOpensRegioJetPage();
        steps.userSearchForConnectionForNextMonday("Ostrava", "Brno");

        steps.printInformationAboutRoutes();
    }

    @Test
    public void obtainDataWithApiCalls() {
        System.out.println("Printing data obtained from API");
        System.out.println("===============================");
        RegioJetApiSteps steps = new RegioJetApiSteps();

        steps.getConnectionRoutesFromOstravaToBrno();
    }
}
