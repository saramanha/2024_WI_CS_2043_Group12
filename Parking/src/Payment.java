import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Payment {
    
    public static double calculatePayment(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        final double initialRate = 3.0;
        final double hourlyRate = 2.5;
        //long durationInMinutes = ChronoUnit.MINUTES.between(checkInTime, checkOutTime);
        long durationInMinutes = ChronoUnit.SECONDS.between(checkInTime, checkOutTime);
        double fee;

        if (durationInMinutes <= 30) {
            fee = initialRate;
        } else {
            double remainingMinutes = durationInMinutes - 30;
            fee = initialRate + Math.ceil(remainingMinutes / 30.0) * hourlyRate;
        }
        return fee;
    }

    public static double calculateAdditionalCharges(LocalDateTime plannedCheckOutTime, LocalDateTime actualCheckOutTime) {
        final double initialLateCharge = 5.0;
        final double hourlyLateCharge = 10.0;
        //long additionalDurationInMinutes = ChronoUnit.MINUTES.between(plannedCheckOutTime, actualCheckOutTime);
        long additionalDurationInMinutes = ChronoUnit.SECONDS.between(plannedCheckOutTime, actualCheckOutTime);

        if (additionalDurationInMinutes <= 0) {
            return 0;
        }

        return initialLateCharge + Math.ceil(additionalDurationInMinutes / 60.0) * hourlyLateCharge;
    }
}











/*
    Duration: 1.0 hours
    Parking Rate: $5 per hour
    Total Amount Paid: 8.0

    Duration: 1.5 hours
    Parking Rate: $5 per hour
    Total Amount Paid: 8.0

    Problem: rate is $5 per hour after initial 0.5 hour as per proposal
    Solution:
        Decide if: only allowed increments (GUI) are by 1 hour except for initial 0.5 hour <- closer to proposal instructions
               or: allow a rate of $2.5 per 0.5 hour after initial <- IMPLEMENTED
*/