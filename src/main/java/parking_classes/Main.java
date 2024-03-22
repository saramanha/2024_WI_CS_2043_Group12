package parking_classes;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Create a parking spot and validate it
        ParkingSpot spot = new ParkingSpot("a1");
        if (!ParkingSpot.isValidSpotId(spot.getSpotId())) {
            System.out.println("Invalid parking spot.");
            return;
        }

        // Register a car
        Registration registration = new Registration();
        registration.assignSpot(spot, "ABC1234");

        // Simulate payment
        Date checkInTime = new Date();
        // Assuming 4 hours parking for demonstration
        Date checkOutTime = new Date(checkInTime.getTime() + 4 * 3600 * 1000);
        double paymentAmount = Payment.calculatePayment(checkInTime, checkOutTime);

        // Generate and display a ticket
        Ticket ticket = new Ticket(spot.getSpotId(), spot.getLicencePlate(), checkInTime, paymentAmount);
        System.out.println(ticket);
    }
}
