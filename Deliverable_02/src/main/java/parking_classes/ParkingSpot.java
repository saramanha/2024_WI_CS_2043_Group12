package parking_classes;

public class ParkingSpot {
    private String spotId;
    private boolean isOccupied;
    private String licencePlate;

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
        this.isOccupied = false;
        this.licencePlate = null;
    }
     public String getSpotId() {
        return spotId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupySpot(String licencePlate) {
        this.isOccupied = true;
        this.licencePlate = licencePlate;
    }

    public void freeSpot() {
        this.isOccupied = false;
        this.licencePlate = null;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
}