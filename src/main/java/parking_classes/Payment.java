package parking_classes;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Payment {
    
    public static double calculatePayment(Date checkInTime, Date checkOutTime) {
        final double initialRate = 3.0;
        final double hourlyRate = 5.0;
        long durationInMinutes = TimeUnit.MILLISECONDS.toMinutes(checkOutTime.getTime() - checkInTime.getTime());
        double fee;

        if (durationInMinutes <= 30.0) {
            fee = initialRate;
        } else {
            double remainingMinutes = durationInMinutes - 30;
            fee = initialRate + Math.ceil(remainingMinutes/60.0)*hourlyRate;
        }
        return fee;
    }

    public static double calculateAdditionalCharges(Date plannedCheckOutTime, Date actualCheckOutTime) {
        final double initialLateCharge = 5.0;
        final double hourlyLateCharge = 10.0;
        long additionalDurationInMinutes = TimeUnit.MILLISECONDS.toMinutes(actualCheckOutTime.getTime() - plannedCheckOutTime.getTime());
        
        if (additionalDurationInMinutes <= 0) {
            return 0;
        }

        return initialLateCharge + Math.ceil(additionalDurationInMinutes/60.0)*hourlyLateCharge;
    }

}