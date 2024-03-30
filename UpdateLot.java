
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.Statement;
	import java.sql.ResultSet;
	import java.sql.SQLException;


public class UpdateLot {
		
		private final Connection CONNECTION; 
		private final String SELECTSTMNT; // the query to db
		private final String PUTSTMNT;
		private final Statement EXECUTESTMNT; // create statement

	private UpdateLot() throws SQLException{ 
			CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/ParkingLotDB", "testuser", "pass");
			SELECTSTMNT = "SELECT * from ParkingLot";
			EXECUTESTMNT = CONNECTION.createStatement();
			PUTSTMNT = "UPDATE ParkingLot SET isOccupied = ?, vehiclePlate = ?, ticketNum = ?, idleTime = ?"
					 + "WHERE spotId = ?;";
	}
	
	/**
	 * compiles a String of data from a parking lot sql base
	 * @return 2d string array rows = id, boolean(isoccupied) as string, plate, ticketnum as string, time spent as string
	 * @return null if SQL errors occur
	 */
	public static String[][] getLotData() {
		try {
			UpdateLot data = new UpdateLot();
			ResultSet rset = data.calculateLotData();
				if (rset == null) throw new SQLException();
			String[][] stringOut =  new String[40][5];
			int i = 0;
			while(rset.next()) {
				if (i < 40) {
					stringOut[i][0] = rset.getString("spotId");
					stringOut[i][1] = Boolean.toString(rset.getBoolean("isOccupied"));
					stringOut[i][2] = rset.getString("vehiclePlate");
					stringOut[i][3] = rset.getString("ticketNum");
					stringOut[i][4] = "" + (calculateIdleTime() - rset.getInt(5));
					i++;
				}
				else{
					break;
				}
			}
			data.CONNECTION.close();
			return stringOut; 
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * sends an update to the sql table when parking is updated
	 * @param dataIn a String array containing 4 rows: id, spot taken status("t" or "f"), plate num, ticket num 
	 * input plate and ticket num as null with "f" as the second col to reset a spot 
	 * @return true if successful, false if sql error occurs
	 */
	public static boolean updateLot(String[] dataIn) {
		try {
			UpdateLot data = new UpdateLot();
			PreparedStatement outStmnt = data.CONNECTION.prepareStatement(data.PUTSTMNT);
			outStmnt.setString(5, dataIn[0]);
			if (dataIn[1] == "t"){
				outStmnt.setBoolean(1, true);
			}
			else{
				outStmnt.setBoolean(1, false);
			}
			outStmnt.setString(2, dataIn[2]);
			outStmnt.setString(3, dataIn[3]);
			outStmnt.setInt(4, calculateIdleTime());
			outStmnt.execute();
			data.CONNECTION.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

		private ResultSet calculateLotData() throws NullPointerException{
			try {
				return EXECUTESTMNT.executeQuery(SELECTSTMNT); // after data is collected, is formatted
			}
			catch(SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		private static int calculateIdleTime() {
			return (int) (System.currentTimeMillis() / 36000000);
		}
	}


