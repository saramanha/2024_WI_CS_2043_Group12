package parking_classes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    @Test
    public void testCalculatePayment() {
        Calendar calendar = Calendar.getInstance();
        Date checkInTime = calendar.getTime();
        calendar.add(Calendar.HOUR, 2);
        Date checkOutTime = calendar.getTime();
        double fee = Payment.calculatePayment(checkInTime, checkOutTime);
        assertEquals(13.0, fee, 0.001);
    }

    @Test
    public void testCalculateAdditionalCharges() {
        Calendar calendar = Calendar.getInstance();
        Date plannedCheckOutTime = calendar.getTime();
        calendar.add(Calendar.HOUR, 1); // 1 hour late
        Date actualCheckOutTime = calendar.getTime();
        double lateFee = Payment.calculateAdditionalCharges(plannedCheckOutTime, actualCheckOutTime);
        assertEquals(15.0, lateFee, 0.001);
    }
}