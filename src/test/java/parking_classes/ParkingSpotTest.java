package parking_classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingSpotTest {
    @Test
    public void testIsValidSpotIdValid() {
        assertTrue(ParkingSpot.isValidSpotId("a1"));
    }

    @Test
    public void testIsValidSpotIdInvalid() {
        assertFalse(ParkingSpot.isValidSpotId("z9"));
    }

    @Test
    public void testOccupyAndFreeSpot() {
        ParkingSpot spot = new ParkingSpot("b2");
        assertFalse(spot.isOccupied());
        spot.occupySpot("ABC123");
        assertTrue(spot.isOccupied());
        assertEquals("ABC123", spot.getLicencePlate());
        spot.freeSpot();
        assertFalse(spot.isOccupied());
        assertNull(spot.getLicencePlate());
    }
}