package lab1;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class Tools {
    public static void printListToFile(ArrayList<Double> list, String path) {
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file, false); // overwrites the file
            for (double d : list){
                writer.write(d + "\n");
            }
            writer.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
