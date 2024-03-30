/**
 * @author Patrick Wilson #3708040 | 
 * displays the full spot list, designating empty spots and printing 
 * reserved spot data.
 */
public class UpdateLotTest{ 

    public static void main(String[] args){
        String[][] results = UpdateLot.getLotData();
        for(int i = 0; i < 40; i++){
            if (results[i][1].compareTo("FALSE") == 0) System.out.println(results[i][0] + "| empty");
            else {
                for (int j = 0; j < 5; j++){
                    System.out.println(results[i][j] + "| ");
                }
            }
        }
    }
}