package parking_classes;

import java.util.Date;

public class Ticket {
    private String spotId;
    private Date checkInTime;
    private Date checkOutTime;
    private double amountPaid;
    private static int ticketIdCounter = 999;
    private int ticketId;
    private String licencePlate;

    public Ticket(String spotId, String licencePlate, Date checkInTime, double amountPaid) {
        this.spotId = spotId;
        this.checkInTime = checkInTime;
        this.amountPaid = amountPaid;
        this.ticketId = ++ticketIdCounter;
        this.licencePlate = licencePlate;
    }

    public String getSpotId() {
        return spotId;
    }
    public Date getCheckInTime() {
        return checkInTime;
    }
    public Date getCheckOutTime() {
        return checkOutTime;
    }
    public double getAmountPaid() {
        return amountPaid;
    }
    public int getTicketId() {
        return ticketId;
    }
    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    @Override
    public String toString() {
        return "-------------------------- PARKING RECEIPT -------------------------" 
             + "\n Receipt ID: " + ticketId
             + "\n Parking Spot: " + spotId
             + "\n Licence Plate: " + licencePlate
             + "\n Check-In Time: " + checkInTime 
             + "\n Check-Out Time: " + checkOutTime 
             + "\n Duration: TODO"
             + "\n Parking Rate: $5 per hour"
             + "\n Total Amount Paid: " + amountPaid 
             + "\n-------------------------------------------------------------------"
             + "\n Thank you for using our Parking Payment System. Have a nice day! "
             + "\n-------------------------------------------------------------------"
            ;
    }
}