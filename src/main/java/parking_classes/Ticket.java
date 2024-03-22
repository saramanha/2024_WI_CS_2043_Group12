package parking_classes;

import java.util.Date;

public class Ticket {
    private String spotId;
    private Date checkInTime;
    private Date checkOutTime;
    private long duration; 
    private double amountPaid;
    private static int ticketIdCounter = 999;
    private int ticketId;
    private String licencePlate;

    public Ticket(String spotId, String licencePlate, Date checkInTime, double amountPaid, long duration) {
        this.spotId = spotId;
        this.checkInTime = checkInTime;
        this.checkOutTime = new Date(checkInTime.getTime() + duration*3600*1000);
        this.amountPaid = amountPaid;
        this.ticketId = ++ticketIdCounter;
        this.licencePlate = licencePlate;
        this.duration = duration;
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
    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public String toString() {
        return "-------------------------- PARKING RECEIPT -------------------------" 
             + "\n Receipt ID: " + ticketId
             + "\n Parking Spot: " + spotId
             + "\n Licence Plate: " + licencePlate
             + "\n Check-In Time: " + checkInTime 
             + "\n Check-Out Time: " + checkOutTime 
             + "\n Duration: " + duration + " hours"
             + "\n Parking Rate: $5 per hour" // To do: make this a variable so that it displays $3 per hour if duration <= 30min
             + "\n Total Amount Paid: " + amountPaid 
             + "\n-------------------------------------------------------------------"
             + "\n Thank you for using our Parking Payment System. Have a nice day! "
             + "\n-------------------------------------------------------------------"
            ;
    }
}