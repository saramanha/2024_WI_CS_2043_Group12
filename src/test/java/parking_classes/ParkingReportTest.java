public class ParkingReportTest {
    public static void main(String[] args) {
        System.out.println("Testing ParkingReport class:");
        
        // Test WEEKLY report
        ParkingReportType weeklyReport = ParkingReportType.WEEKLY;
        System.out.println("Weekly Report Name: " + weeklyReport.getTypeName());
        
        // Test MONTHLY report
        ParkingReportType monthlyReport = ParkingReportType.MONTHLY;
        System.out.println("Monthly Report Name: " + monthlyReport.getTypeName());
        
        // Test toString method
        System.out.println(weeklyReport);
        System.out.println(monthlyReport);
    }
}
