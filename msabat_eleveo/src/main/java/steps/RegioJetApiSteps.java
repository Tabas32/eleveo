package steps;

import api.RegioJetClient;
import models.api.Route;
import java.util.List;
import static utils.DateUtility.getNextMondayDay;

/**
 * Steps related to regiojet page
 */
public class RegioJetApiSteps {

    private final String DATE_FORMAT = "yyyy-MM-dd";

    public void getConnectionRoutesFromOstravaToBrno() {
        List<Route> routes = RegioJetClient.getInstance().getRoutes(
                "10202000",
                "10202002",
                getNextMondayDay(DATE_FORMAT)
        );

        routes.stream().forEach( route -> {
            System.out.println("\n===========================================");
            System.out.println("Departure time: " + route.getDepartureTime());
            System.out.println("Arrival time: " + route.getArrivalTime());
            System.out.println("Transfers: " + route.getTransfersCount());
            System.out.println("Price from: " + route.getPriceFrom());
            System.out.println("Travel time: " + route.getTravelTime());
        });
    }
}
