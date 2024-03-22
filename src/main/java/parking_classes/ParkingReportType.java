public class ParkingReportType {
    public static final ParkingReportType WEEKLY = new ParkingReportType("WEEKLY REPORT");
    public static final ParkingReportType MONTHLY = new ParkingReportType("MONTHLY REPORT");
    
    private final String typeName;

    private ParkingReportType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public String toString() {
        return this.typeName;
    }

    public static void main(String[] args) {
        ParkingReportType reportType = ParkingReportType.WEEKLY;
        System.out.println("Report Type: " + reportType);
    }
}
