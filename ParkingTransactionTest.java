import java.time.LocalDateTime;

public class ParkingTransactionTest {
    public static void main(String[] args) {
        // Create a sample parking transaction with no late charge
        ParkingTransaction transaction1 = new ParkingTransaction(
            "TXN123",
            "A1",
            "ABC123",
            LocalDateTime.of(2024, 3, 22, 9, 0),
            LocalDateTime.of(2024, 3, 22, 13, 0),
            20.0,
            "Credit Card"
        );

        // Create a sample parking transaction with a late charge
        ParkingTransaction transaction2 = new ParkingTransaction(
            "TXN456",
            "B2",
            "XYZ789",
            LocalDateTime.of(2024, 3, 22, 10, 0),
            LocalDateTime.of(2024, 3, 22, 14, 0),
            20.0,
            "Debit Card"
        );

        // Actual departure time for the first transaction (on time)
        LocalDateTime actualDeparture1 = LocalDateTime.of(2024, 3, 22, 13, 0);

        // Actual departure time for the second transaction (late)
        LocalDateTime actualDeparture2 = LocalDateTime.of(2024, 3, 22, 15, 30); // 1.5 hours late

        // Print details of the first transaction
        System.out.println(transaction1);

        // Check for late charge and print if any for the first transaction
        String lateCharge1 = transaction1.lateChargeToString(actualDeparture1);
        if (!lateCharge1.isEmpty()) {
            System.out.println(lateCharge1);
        }

        // Print details of the second transaction
        System.out.println(transaction2);

        // Check for late charge and print if any for the second transaction
        String lateCharge2 = transaction2.lateChargeToString(actualDeparture2);
        if (!lateCharge2.isEmpty()) {
            System.out.println(lateCharge2);
        }
    }
}
