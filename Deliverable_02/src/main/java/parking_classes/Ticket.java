package parking_classes;

import java.util.Date;

public class Ticket {
    private String spotId;
    private Date checkInTime;
    private Date checkOutTime;
    private double amountPaid;
    private static int ticketIdCounter = 999;
    private int ticketId;

    public Ticket(String spotId, Date checkInTime, double amountPaid) {
        this.spotId = spotId;
        this.checkInTime = checkInTime;
        this.amountPaid = amountPaid;
        this.ticketId = ++ticketIdCounter;
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
        return "Ticket{" +
                "spotNumber='" + spotId + '\'' +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", amountPaid=" + amountPaid +
                ", ticketId=" + ticketId +
                '}';
    }
}