package parking_classes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketTest {
    @Test
    public void testTicketCreation() {
        Calendar calendar = Calendar.getInstance();
        Date checkInTime = calendar.getTime();
        calendar.add(Calendar.HOUR, 3);
        double amountPaid = 15.0; 
        Ticket ticket = new Ticket("a1", "ABC123", checkInTime, amountPaid, 3);
        assertEquals("a1", ticket.getSpotId());
        assertEquals("ABC123", ticket.getLicencePlate());
        assertEquals(15.0, ticket.getAmountPaid(), 0.001);
        assertNotNull(ticket.getCheckInTime());
        assertNotNull(ticket.getCheckOutTime());
    }
}
