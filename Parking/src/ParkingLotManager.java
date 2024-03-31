import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Statement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class ParkingLotManager {
    
    private final Connection CONNECTION; 
    //private final String SELECTSTMNT; // the query to db
    private final String PUTSTMNT;
    //private final Statement EXECUTESTMNT; // create statement

    public ParkingLotManager() throws SQLException{ 
        //CONNECTION = DriverManager.getConnection("jdbc:mysql://cs1103.cs.unb.ca:", "n93gf", "");
        CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkingLotDB", "testuser", "pass");
        //SELECTSTMNT = "SELECT * from ParkingLot";
        //EXECUTESTMNT = CONNECTION.createStatement();
        PUTSTMNT = "UPDATE ParkingLot SET isOccupied = ?, vehiclePlate = ?, ticketId = ?, checkInTime = ?, checkOutTime = ?, durationInHours = ? WHERE spotID = ?";
    }

    public Ticket parkVehicle(String spotId, String licencePlate, double durationInHours) throws SQLException {
        // Create a new ticket
        LocalDateTime checkInTime = LocalDateTime.now();
        Ticket ticket = new Ticket(spotId, licencePlate, checkInTime, durationInHours);
        try {
            PrintWriter writer = new PrintWriter("./bin/currentTicket.txt");
            writer.println(ticket);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while trying to write to /bin/currentTicket.txt");
            e.printStackTrace();
        }
        // Send some ticket info to database
        try {
            PreparedStatement outStmnt = this.CONNECTION.prepareStatement(this.PUTSTMNT);
            LocalDateTime checkOutTime = checkInTime.plusHours((long) durationInHours);
        
            outStmnt.setBoolean(1, true);
            outStmnt.setString(2, licencePlate);
            outStmnt.setInt(3, ticket.getTicketId());
            outStmnt.setTimestamp(4, Timestamp.valueOf(checkInTime));
            outStmnt.setTimestamp(5, Timestamp.valueOf(checkOutTime));
            outStmnt.setDouble(6, durationInHours);
            outStmnt.setString(7, spotId);
            
            outStmnt.executeUpdate();
            
            return ticket;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // To do: 
    /* public lateFeeTicket removeVehicle(Ticket ticket) {
        
    }*/

}