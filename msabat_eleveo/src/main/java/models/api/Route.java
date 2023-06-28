package models.api;

import java.util.List;

public class Route {
    private String id;
    private int departureStationId;
    private String departureTime;
    private long arrivalStationId;
    private String arrivalTime;
    private List<String> vehicleTypes;
    private int transfersCount;
    private int freeSeatsCount;
    private double priceFrom;
    private double priceTo;
    private double creditPriceFrom;
    private double creditPriceTo;
    private int pricesCount;
    private Object actionPrice;
    private boolean surcharge;
    private boolean notices;
    private boolean support;
    private boolean nationalTrip;
    private boolean bookable;
    private Object delay;
    private String travelTime;
    private String vehicleStandardKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public long getArrivalStationId() {
        return arrivalStationId;
    }

    public void setArrivalStationId(long arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<String> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public int getTransfersCount() {
        return transfersCount;
    }

    public void setTransfersCount(int transfersCount) {
        this.transfersCount = transfersCount;
    }

    public int getFreeSeatsCount() {
        return freeSeatsCount;
    }

    public void setFreeSeatsCount(int freeSeatsCount) {
        this.freeSeatsCount = freeSeatsCount;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public double getCreditPriceFrom() {
        return creditPriceFrom;
    }

    public void setCreditPriceFrom(double creditPriceFrom) {
        this.creditPriceFrom = creditPriceFrom;
    }

    public double getCreditPriceTo() {
        return creditPriceTo;
    }

    public void setCreditPriceTo(double creditPriceTo) {
        this.creditPriceTo = creditPriceTo;
    }

    public int getPricesCount() {
        return pricesCount;
    }

    public void setPricesCount(int pricesCount) {
        this.pricesCount = pricesCount;
    }

    public Object getActionPrice() {
        return actionPrice;
    }

    public void setActionPrice(Object actionPrice) {
        this.actionPrice = actionPrice;
    }

    public boolean isSurcharge() {
        return surcharge;
    }

    public void setSurcharge(boolean surcharge) {
        this.surcharge = surcharge;
    }

    public boolean isNotices() {
        return notices;
    }

    public void setNotices(boolean notices) {
        this.notices = notices;
    }

    public boolean isSupport() {
        return support;
    }

    public void setSupport(boolean support) {
        this.support = support;
    }

    public boolean isNationalTrip() {
        return nationalTrip;
    }

    public void setNationalTrip(boolean nationalTrip) {
        this.nationalTrip = nationalTrip;
    }

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public Object getDelay() {
        return delay;
    }

    public void setDelay(Object delay) {
        this.delay = delay;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getVehicleStandardKey() {
        return vehicleStandardKey;
    }

    public void setVehicleStandardKey(String vehicleStandardKey) {
        this.vehicleStandardKey = vehicleStandardKey;
    }
}

