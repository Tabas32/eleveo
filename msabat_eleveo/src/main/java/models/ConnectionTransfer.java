package models;

/**
 * POJO storing data about connection transfer
 */
public class ConnectionTransfer {

    private String originCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private String length;

    public ConnectionTransfer() {
        this.originCity = null;
        this.destinationCity = null;
        this.departureTime = null;
        this.arrivalTime = null;
        this.length = null;
    }

    public ConnectionTransfer(String originCity, String destinationCity, String departureTime, String arrivalTime, String length) {
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.length = length;
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
