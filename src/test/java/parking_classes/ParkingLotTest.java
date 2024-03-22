package parking_classes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    public void testParkVehicleInvalidSpot() {
        ParkingLot lot = new ParkingLot();
        lot.parkVehicle("z9", "ABC123", new Date(), 2);
    }

    @Test
    public void testParkVehicleInvalidPlate() {
        ParkingLot lot = new ParkingLot();
        Date checkInTime = new Date();
        Ticket ticket = lot.parkVehicle("a1", "TOOLONGPLATE", checkInTime, 2);
        assertNull(ticket);
    }
}
