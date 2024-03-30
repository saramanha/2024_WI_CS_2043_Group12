/**
 * @author Patrick Wilson #3708040 | 
 * uses command lines args: "SpotID"
 * command line arg determines which spot's ticket and plate nums and time
 * are nullified and the spot boolean set to false
 */
public class UpdateLotResetTest{

    public static void main(String[] args){
        if (UpdateLot.resetSpot(args)) System.out.println("sucessfully deleted all data in spot and set it to empty");
        else System.out.println("Failed to reset spot, sql error");
        return;
    }
}