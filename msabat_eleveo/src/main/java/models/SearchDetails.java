package models;

/**
 * POJO storing data used as input for search
 */
public class SearchDetails {

    private String originCity;
    private String destinationCity;
    private String departureDate;

    public SearchDetails() {
        this.originCity = null;
        this.destinationCity = null;
        this.departureDate = null;
    }

    public SearchDetails(String originCity, String destinationCity, String departureDate) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureDate = departureDate;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
