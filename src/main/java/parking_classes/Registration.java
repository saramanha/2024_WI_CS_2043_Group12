package parking_classes;

public class Registration {
    public void assignSpot(ParkingSpot spot, String licencePlate) {
        spot.occupySpot(licencePlate);
    }
}

