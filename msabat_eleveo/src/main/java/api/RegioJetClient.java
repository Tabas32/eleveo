package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import models.api.Route;

import java.util.List;

public class RegioJetClient {

    private static RegioJetClient instance;

    private RegioJetFeign feign = buildClient();
    public static RegioJetClient getInstance() {
        if (instance == null) {
            synchronized (RegioJetClient.class) {
                if (instance == null) {
                    instance = new RegioJetClient();
                }
            }
        }

        return instance;
    }

    private RegioJetClient() {}

    public List<Route> getRoutes(
            String originId,
            String destinationId,
            String date
    ) {

        return feign.getRoutes(originId, destinationId, date).getRoutes();
    }

    private RegioJetFeign buildClient() {
        ObjectMapper mapper = new ObjectMapper();

        return Feign.builder()
                .encoder(new FormEncoder(new JacksonEncoder(mapper)))
                .decoder(new JacksonDecoder(mapper))
                .target(RegioJetFeign.class, "https://brn-ybus-pubapi.sa.cz");
    }
}
