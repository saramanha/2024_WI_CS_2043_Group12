package parking_classes;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        
        ParkingLot parkingLot = new ParkingLot();
        
        Date checkInTime = new Date();
    
        Ticket ticket = parkingLot.parkVehicle("a1", "XYZ789", checkInTime, 4);
        Ticket ticket2 = parkingLot.parkVehicle("b4", "ABC123", checkInTime, 1);
        // To do: can't enter duration as 0.5 since duration must be long to work with date

        System.out.println(ticket);
        System.out.println(ticket2);
        
    }
}
