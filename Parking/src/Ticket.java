import java.time.LocalDateTime;

public class Ticket {
    private String spotId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private double durationInHours;
    private double amountPaid;
    private static int ticketIdCounter = 999;
    private int ticketId;
    private String licencePlate;

    public Ticket(String spotId, String licencePlate, LocalDateTime checkInTime, double durationInHours) {
        this.spotId = spotId;
        this.licencePlate = licencePlate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkInTime.plusMinutes((long)(60*durationInHours));
        this.amountPaid = Payment.calculatePayment(checkInTime, checkOutTime);
        this.durationInHours = durationInHours;
        this.ticketId = ++ticketIdCounter;
    }

    public int getTicketId() {
        return ticketId;
    }

    // To do:
    /* public lateFeeTicket */

    @Override
    public String toString() {
        return "-------------------------- PARKING RECEIPT ------------------------" 
             + "\n Receipt ID: " + ticketId
             + "\n Parking Spot: " + spotId
             + "\n Licence Plate: " + licencePlate
             + "\n Check-In Time: " + checkInTime 
             + "\n Check-Out Time: " + checkOutTime 
             + "\n Duration: " + durationInHours + " hours"
             + "\n Parking Rate: " + (durationInHours <= 0.5 ? "$3 per 30 minutes" : "$5 per hour")
             + "\n Total Amount Paid: $" + amountPaid 
             + "\n-------------------------------------------------------------------"
             + "\n Thank you for using our Parking Payment System. Have a nice day! "
             + "\n-------------------------------------------------------------------\n"
            ;
    }
}
