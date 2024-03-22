package parking_classes;

public class ParkingSpot {
    private String spotId;
    private boolean isOccupied;
    private String licencePlate;

    public ParkingSpot(String spotId) {
        if (isValidSpotId(spotId)) {
            this.spotId = spotId;
        } else {
            throw new IllegalArgumentException("Invalid spot ID. Must be a letter from 'a' to 'e' followed by a number from 1 to 8.");
        }
        this.isOccupied = false;
        this.licencePlate = null;
    }

    public static boolean isValidSpotId(String spotId) {
        return spotId.matches("^[a-e][1-8]$");
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