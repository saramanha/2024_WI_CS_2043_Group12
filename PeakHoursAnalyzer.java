import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeakHoursAnalyzer {

    private List<ParkingTransaction> transactions;

    public PeakHoursAnalyzer(List<ParkingTransaction> transactions) {
        this.transactions = transactions;
    }

    public String analyzePeakHours() {
        // Map to hold counts of transactions per hour
        Map<Integer, Integer> hourCount = new HashMap<>();

        for (ParkingTransaction transaction : transactions) {
            LocalDateTime checkInTime = transaction.getCheckIn();
            int hour = checkInTime.getHour(); 

            hourCount.put(hour, hourCount.getOrDefault(hour, 0) + 1);
        }

        // Find the peak hour(s)
        int maxCount = hourCount.values().stream().max(Integer::compare).orElse(0);

        StringBuilder peakHours = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : hourCount.entrySet()) {
            if (entry.getValue() == maxCount) {
                if (peakHours.length() > 0) peakHours.append(",\n");
                peakHours.append(String.format("%02d:00 - %02d:00", entry.getKey(), entry.getKey() + 1));
            }
        }

        return "Peak Hour(s):\n" + peakHours.toString() + "\nTotal Transactions During Peak Hour(s): " + maxCount;
    }
}
