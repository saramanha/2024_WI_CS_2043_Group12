
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
			CONNECTION = DriverManager.getConnection("jdbc:mysql://cs1103.cs.unb.ca:", "pwilson3", "xxxx");
			SELECTSTMNT = "select * from ParkingLot";
			EXECUTESTMNT = CONNECTION.createStatement();
			PUTSTMNT = "INSERT INTO ParkingLot (spotId, isOccupied, vehiclePlate, vehicleDesc, idleTime"
					+ "VALUES (?,?,?,?,?))";
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
					stringOut[0][i] += rset.getString("spotId") + " ";
					stringOut[1][i] += rset.getBoolean("isOccupied");
					stringOut[2][i] += " " + rset.getString("vehiclePlate")+" ";
					stringOut[3][i] += rset.getString("vehicleDesc");
					stringOut[4][i] += " " + (data.calculateIdleTime() - rset.getInt("idleTime"));
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
			outStmnt.setString(1, dataIn[0]);
			outStmnt.setBoolean(2, true);
			outStmnt.setString(3, dataIn[1]);
			outStmnt.setString(4, dataIn[2]);
			outStmnt.setInt(5, calculateIdleTime());
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


