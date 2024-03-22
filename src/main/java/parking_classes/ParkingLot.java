package parking_classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<String, ParkingSpot> spots;

    public ParkingLot() {
        this.spots = new HashMap<>();
        for (char section = 'a'; section <= 'e'; section++) {
            for (int number = 1; number <= 8; number++) {
                String spotId = "" + section + number;
                this.spots.put(spotId, new ParkingSpot(spotId));
            }
        }
    }

    public boolean addParkingSpot(String spotId) {
        if (ParkingSpot.isValidSpotId(spotId) && !spots.containsKey(spotId)) {
            spots.put(spotId, new ParkingSpot(spotId));
            return true;
        }
        return false;
    }

    public Ticket parkVehicle(String spotId, String licencePlate, Date checkInTime, long duration) {
        // To do: ensure that overnight parking is not possible as per instructions.
        if (licencePlate.length() > 7) {
            System.out.println("License plate number exceeds maximum length of 7 characters.");
            return null;
        }
        ParkingSpot spot = spots.get(spotId);
        if (spot != null && !spot.isOccupied()) {
            spot.occupySpot(licencePlate);
            Date checkOutTime = new Date(checkInTime.getTime() + duration*3600*1000); 
            double paymentAmount = Payment.calculatePayment(checkInTime, checkOutTime);
            Ticket ticket = new Ticket(spot.getSpotId(), licencePlate, checkInTime, paymentAmount, duration);
            return ticket;
        }
        return null;
    }

    //  To do: implement additional functionalities ex. finding available spots
}

