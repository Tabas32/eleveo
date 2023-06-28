package api;

import feign.Param;
import feign.RequestLine;
import models.api.RouteResponse;

public interface RegioJetFeign {

    @RequestLine("GET /restapi/routes/search/simple?tariffs=REGULAR&toLocationType=CITY&toLocationId={destination}&fromLocationType=CITY&fromLocationId={origin}&departureDate={date}&fromLocationName=&toLocationName=")
    RouteResponse getRoutes(
            @Param("origin") String originId,
            @Param("destination") String destinationId,
            @Param("date") String date
    );
}
