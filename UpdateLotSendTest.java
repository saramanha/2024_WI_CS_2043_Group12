/**
 * @author Patrick Wilson #3708040 | 
 * uses command line args: "SpotID" "PlateNum" "TicketNum"
 * takes command line args and inserts them into the DB to reserve
 * a spot 
 */
public class UpdateLotSendTest{

    public static void main(String[] args){
        if (UpdateLot.updateLot(args))System.out.println("successfully reserved a spot");
        else System.out.println("Failed to reserve a spot, sql error");
        return;
    }
}