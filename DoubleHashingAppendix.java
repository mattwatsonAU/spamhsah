/**
 * Created by mwatson on 12/10/15.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DoubleHashingAppendix {
    private static DoubleHashMap<String, Double> DoubleHashMapA = new DoubleHashMap<String, Double>(2000,1, 4271, 1);
    private static DoubleHashMap<String, Double> DoubleHashMapB = new DoubleHashMap<String, Double>(2000,1, 4271,223);
    private static DoubleHashMap<String, Double> DoubleHashMapC = new DoubleHashMap<String, Double>(2000,1, 4271,647);
    private static DoubleHashMap<String, Double> DoubleHashMapD = new DoubleHashMap<String, Double>(1, 4271,1);
    private static DoubleHashMap<String, Double> DoubleHashMapE = new DoubleHashMap<String, Double>(1, 4271,223);
    private static DoubleHashMap<String, Double> DoubleHashMapF = new DoubleHashMap<String, Double>(1, 4271,647);

    public static void main(String[] args)  {
        System.out.println("DOUBLE HASHING: RESULTS");
        System.out.println("");

        try {
            exploreData("datasetA.txt");
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
                    DoubleHashMapA.put(ip, observedFreq);
                    DoubleHashMapB.put(ip, observedFreq);
                    DoubleHashMapC.put(ip, observedFreq);
                    DoubleHashMapD.put(ip, observedFreq);
                    DoubleHashMapE.put(ip, observedFreq);
                    DoubleHashMapF.put(ip, observedFreq);

                }
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        printCollisionStatsSummary();
    }

    private static void printCollisionStatsSummary(){
        System.out.println("**COLLISION STATS: (1,4271,1,2000)**");
        System.out.println("Put Collisions: " + DoubleHashMapA.putCollisions());
        System.out.println("Total Collisions: " + DoubleHashMapA.totalCollisions());
        System.out.println("Maximum Collisions: " + DoubleHashMapA.maxCollisions());
        System.out.println("Put Failures: " + DoubleHashMapA.putFailures());
        System.out.println("");

        System.out.println("**COLLISION STATS: (10,4271,223,2000)**");
        System.out.println("Put Collisions: " + DoubleHashMapB.putCollisions() );
        System.out.println("Total Collisions: " + DoubleHashMapB.totalCollisions());
        System.out.println("Maximum Collisions: " + DoubleHashMapB.maxCollisions());
        System.out.println("Put Failures: " + DoubleHashMapB.putFailures());
        System.out.println("");

        System.out.println("**COLLISION STATS: (1,4271,647,2000)**");
        System.out.println("Put Collisions: " + DoubleHashMapC.putCollisions() );
        System.out.println("Total Collisions: " + DoubleHashMapC.totalCollisions());
        System.out.println("Maximum Collisions: " + DoubleHashMapC.maxCollisions());
        System.out.println("Put Failures: " + DoubleHashMapC.putFailures());
        System.out.println("");

        System.out.println("**COLLISION STATS: (1,4271,1,4000)**");
        System.out.println("Put Collisions: " + DoubleHashMapD.putCollisions());
        System.out.println("Total Collisions: " + DoubleHashMapD.totalCollisions());
        System.out.println("Maximum Collisions: " + DoubleHashMapD.maxCollisions());
        System.out.println("Put Failures: " + DoubleHashMapD.putFailures());
        System.out.println("");

        System.out.println("**COLLISION STATS: (1,4271,223, 4000)**");
        System.out.println("Put Collisions: " + DoubleHashMapE.putCollisions() );
        System.out.println("Total Collisions: " + DoubleHashMapE.totalCollisions());
        System.out.println("Maximum Collisions: " + DoubleHashMapE.maxCollisions());
        System.out.println("Put Failures: " + DoubleHashMapE.putFailures());
        System.out.println("");

        System.out.println("**COLLISION STATS: (1,4271,647,4000)**");
        System.out.println("Put Collisions: " + DoubleHashMapF.putcCollisions() );
        System.out.println("Total Collisions: " + DoubleHashMapF.totalCollisions());
        System.out.println("Maximum Collisions: " + DoubleHashMapF.maxCollisions());
        System.out.println("Put Failures: " + DoubleHashMapF.putFailures());
        System.out.println("");

    }
}
