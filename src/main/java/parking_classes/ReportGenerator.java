import java.time.*;
import java.time.temporal.*;
import java.util.stream.*;
import java.util.Map;
import java.util.List;

public class ReportGenerator {

    private List<ParkingTransaction> transactions;

    public ReportGenerator(List<ParkingTransaction> transactions) {
        this.transactions = transactions;
    }

    private List<ParkingTransaction> filterTransactionsByDate(LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
            .filter(t -> !t.getCheckIn().toLocalDate().isBefore(startDate) && !t.getCheckIn().toLocalDate().isAfter(endDate))
            .collect(Collectors.toList());
    }

    public String generateReport(ParkingReportType type) {
        if (ParkingReportType.WEEKLY.equals(type)) {
            return generateWeeklyReport();
        } else if (ParkingReportType.MONTHLY.equals(type)) {
            return generateMonthlyReport();
        } else {
            throw new IllegalArgumentException("Unsupported report type: " + type.getTypeName());
        }
    }

    private String generateWeeklyReport() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = startOfWeek.plusDays(6);
    
        List<ParkingTransaction> weeklyTransactions = filterTransactionsByDate(startOfWeek, endOfWeek);
    
        double totalFees = weeklyTransactions.stream()
            .mapToDouble(ParkingTransaction::getFee)
            .sum();
    
        return " WEEKLY REPORT\n" +
               "Report Period: " + startOfWeek + " to " + endOfWeek + "\n" +
               "------------------------------------\n" +
               "Total Fees Collected: $" + String.format("%.2f", totalFees) + "\n" +
               "Number of Transactions: " + weeklyTransactions.size() + "\n" +
               "------------------------------------\n";
    }

    private String generateMonthlyReport() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
    
        List<ParkingTransaction> monthlyTransactions = filterTransactionsByDate(startOfMonth, endOfMonth);
    
        double totalFees = monthlyTransactions.stream()
            .mapToDouble(ParkingTransaction::getFee)
            .sum();
    
        return " MONTHLY REPORT\n" +
               "Report Period: " + startOfMonth + " to " + endOfMonth + "\n" +
               "------------------------------------\n" +
               "Total Fees Collected: $" + String.format("%.2f", totalFees) + "\n" +
               "Number of Transactions: " + monthlyTransactions.size() + "\n" +
               "------------------------------------\n";
    }
}

