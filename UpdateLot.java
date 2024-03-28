
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
			CONNECTION = DriverManager.getConnection("jdbc:mysql://cs1103.cs.unb.ca:", "n93gf", "sGBW8PV0");
			SELECTSTMNT = "select * from ParkingLot";
			EXECUTESTMNT = CONNECTION.createStatement();
			PUTSTMNT = "UPDATE ParkingLot SET isOccupied = ?, vehiclePlate = ?, ticketNum = ?, idleTime = ?"
					+ "WHERE spotId = ?;";
	}
	
	/**
	 * compiles a String of data from a parking lot sql base
	 * @return 2d string array rows = id, bool(isOccupied), plate, description, time spent
	 * @return null if SQL errors occur
	 */
	public static String[][] getLotData() {
		try {
			UpdateLot data = new UpdateLot();
			ResultSet rset = data.calculateLotData();
				if (rset == null) throw new SQLException();
			String[][] stringOut =  new String[5][40];
			int i = 0;
			while(rset.next()) {
				if (i < 40) {
					stringOut[i][0] += rset.getString("spotId") + " ";
					stringOut[i][1] += rset.getBoolean("isOccupied");
					stringOut[i][2] += " " + rset.getString("vehiclePlate")+" ";
					stringOut[i][3] += rset.getString("vehicleDesc");
					stringOut[i][4] += " " + (data.calculateIdleTime() - rset.getInt(5));
					i++;
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
	 * @param dataIn a String containing 3 rows: id, plate, description
	 */
	public void updateLot(String[] dataIn) {
		try {
			PreparedStatement outStmnt = CONNECTION.prepareStatement(PUTSTMNT);
			outStmnt.setString(5, dataIn[0]);
			outStmnt.setBoolean(1, true);
			outStmnt.setString(2, dataIn[1]);
			outStmnt.setString(3, dataIn[2]);
			outStmnt.setInt(4, calculateIdleTime());
			outStmnt.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				CONNECTION.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
		
		
		private int calculateIdleTime() {
			return (int) (System.currentTimeMillis() / 36000000);
		}
	}


