import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingTransaction {
    private String transactionId;
    private String spotId;
    private String plateNumber;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private double fee;
    private String paymentMethod;
    private static final double RATE_PER_HOUR = 5.0; // Assuming $5 per hour rate
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");

    public ParkingTransaction(String transactionId, String spotId, String plateNumber, LocalDateTime checkIn, LocalDateTime checkOut, double fee, String paymentMethod) {
        this.transactionId = transactionId;
        this.spotId = spotId;
        this.plateNumber = plateNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.fee = fee;
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId(){
        return transactionId;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPaymentMethod(){
        return this.paymentMethod;
    }

    // Method to calculate parking fees per hour.
    public double calculateDurationHours(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.toHours() + (duration.toMinutes() % 60 / 60.0);
    }

    public String toString() {
        return "---- PARKING RECEIPT ----\n" + "Transaction ID: " + transactionId + "\n" + "Parking Spot: " + spotId + "\n" + "License Plate: " + plateNumber + "\n" + "Check-In Time: " + formatter.format(checkIn) + "\n" + 
            "Check-Out Time: " + formatter.format(checkOut) + "\n" + "Duration: " + calculateDurationHours(checkIn, checkOut) + " hours\n" + "Parking Rate: $" + RATE_PER_HOUR + " per hour\n" + "Total Amount Paid: $" + String.format("%.2f", fee) + "\n" + "Payment Method: " + paymentMethod + "\n" +
            "----------------------------------\n" + "Thank you for using our Parking Payment System. Have a nice day!\n" + "----------------------------------\n";
    }

    // Method to calculate late payment fees
    private double latePaymentFees(LocalDateTime departureTime) {
        if (departureTime.isAfter(checkOut)) {
            // Calculate the duration exceeded beyond the initial planned check-out time
            long minutesExceeded = Duration.between(checkOut, departureTime).toMinutes();
            double hoursExceeded = minutesExceeded / 60.0;
            double additionalCharges = Math.ceil(hoursExceeded) * RATE_PER_HOUR; // Charge for every started hour
            return additionalCharges;
        }
        return 0.0;
    }

    public String lateChargeToString(LocalDateTime actualDepartureTime) {
        if (actualDepartureTime.isAfter(checkOut)) {
            double additionalCharge = latePaymentFees(actualDepartureTime); // Corrected this line
            double newTotalAmount = fee + additionalCharge;
            long lateDurationHours = Duration.between(checkOut, actualDepartureTime).toHours();
            long lateDurationMinutes = Duration.between(checkOut, actualDepartureTime).toMinutes() % 60;
    
            return "---- LATE CHARGE NOTICE ----\n" + "Transaction ID: " + transactionId + "\n" + "Parking Spot: " + spotId + "\n" + "License Plate: " + plateNumber + "\n" +
                   "Original Check-Out Time: " + formatter.format(checkOut) + "\n" + "Actual Departure Time: " + formatter.format(actualDepartureTime) + "\n" +
                   "Late Duration: " + lateDurationHours + " hour " + lateDurationMinutes + " minutes\n" + "Additional Charge: $" + String.format("%.2f", additionalCharge) + "\n" + 
                   "New Total Amount: $" + String.format("%.2f", newTotalAmount) + "\n" +
                   "----------------------------------\n" + "Please proceed to the nearest payment machine to settle the late charge.\n" + 
                   "Thank you for using our Parking Payment System.\n" + "----------------------------------\n";
        } else {
            // No late charge notice if the departure time is not after the scheduled check-out time.
            return "";
        }
    }
    
}
