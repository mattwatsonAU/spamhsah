/**
 * Created by mwatson on 12/10/15.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LinearProbeAppendix {
    private static HashMap<String, Double> hashMapA = new HashMap<String, Double>(1, 4000);
    private static HashMap<String, Double> hashMapB = new HashMap<String, Double>(10, 4000);
    private static HashMap<String, Double> hashMapC = new HashMap<String, Double>(1, 4271);
    private static HashMap<String, Double> hashMapD = new HashMap<String, Double>(5, 4271);
    private static HashMap<String, Double> hashMapE = new HashMap<String, Double>(2000, 1, 4271);

    public static void main(String[] args)  {
        System.out.println("LINEAR PROBING: RESULTS");
        System.out.println("");

        try {
            exploreData("src/datasetA.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exploreData(String pathToFile) throws FileNotFoundException, IOException {
        // instantiate hash maps
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        try {
            String line = br.readLine();
            while (line != null) {
                String[] pieces = line.trim().split("\\s+");

                if (pieces.length == 4) {
                    String ip = pieces[0];
                    Double observedFreq = Double.parseDouble(pieces[1]);
                    hashMapA.put(ip, observedFreq);
                    hashMapB.put(ip, observedFreq);
                    hashMapC.put(ip, observedFreq);
                    hashMapD.put(ip, observedFreq);
                    hashMapE.put(ip, observedFreq);

                }
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        printCollisionStatsSummary();
    }

    private static void printCollisionStatsSummary(){
        System.out.println("**COLLISION STATS: (1,4000)**");
        System.out.println("Put Collisions: "+hashMapA.putCollisions());
        System.out.println("Total Collisions: "+hashMapA.totalCollisions());
        System.out.println("Maximum Collisions: "+hashMapA.maxCollisions());
        System.out.println("");

        System.out.println("**COLLISION STATS: (10,4000)**");
        System.out.println("Put Collisions: "+hashMapB.putCollisions() );
        System.out.println("Total Collisions: "+hashMapB.totalCollisions());
        System.out.println("Maximum Collisions: "+hashMapB.maxCollisions());
        System.out.println("");

        System.out.println("**COLLISION STATS: (1,4271)**");
        System.out.println("Put Collisions: "+hashMapC.putCollisions() );
        System.out.println("Total Collisions: "+hashMapC.totalCollisions());
        System.out.println("Maximum Collisions: "+hashMapC.maxCollisions());
        System.out.println("");

        System.out.println("**COLLISION STATS: (5,4271)**");
        System.out.println("Put Collisions: "+hashMapD.putCollisions());
        System.out.println("Total Collisions: "+hashMapD.totalCollisions());
        System.out.println("Maximum Collisions: "+hashMapD.maxCollisions());
        System.out.println("");

        System.out.println("**COLLISION STATS: (1,4271, 2000)**");
        System.out.println("Put Collisions: "+hashMapE.putCollisions() );
        System.out.println("Total Collisions: "+hashMapE.totalCollisions());
        System.out.println("Maximum Collisions: "+hashMapE.maxCollisions());
        System.out.println("");

    }
}
