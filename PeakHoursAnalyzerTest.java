import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class PeakHoursAnalyzerTest {
    public static void main(String[] args) {

        List<ParkingTransaction> transactions = new ArrayList<>();
        transactions.add(new ParkingTransaction(
            "TXN001", "A1", "ABC123", LocalDateTime.of(2024, 3, 1, 8, 0),
            LocalDateTime.of(2024, 3, 1, 12, 0), 20.0, "Credit Card"));
        transactions.add(new ParkingTransaction(
            "TXN002", "B2", "XYZ789", LocalDateTime.of(2024, 3, 2, 9, 30),
            LocalDateTime.of(2024, 3, 2, 13, 30), 16.0, "Debit Card"));
        transactions.add(new ParkingTransaction(
            "TXN003", "C3", "LMN456", LocalDateTime.of(2024, 3, 3, 10, 0),
            LocalDateTime.of(2024, 3, 3, 14, 0), 20.0, "Cash"));
        transactions.add(new ParkingTransaction(
            "TXN004", "A1", "DEF123", LocalDateTime.of(2024, 3, 4, 11, 0),
            LocalDateTime.of(2024, 3, 4, 15, 0), 20.0, "Credit Card"));
        transactions.add(new ParkingTransaction(
            "TXN005", "B2", "GHI789", LocalDateTime.of(2024, 3, 5, 12, 0),
            LocalDateTime.of(2024, 3, 5, 18, 0), 30.0, "Credit Card")); 

        PeakHoursAnalyzer analyzer = new PeakHoursAnalyzer(transactions);
        String peakHoursReport = analyzer.analyzePeakHours();
        System.out.println(peakHoursReport);
    }
}
