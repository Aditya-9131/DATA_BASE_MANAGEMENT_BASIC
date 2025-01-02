import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

public class q1 {
    public static void main(String[] args) throws IOException {
        File f1 = new File("C:\\Users\\Dell\\OneDrive\\Desktop\\DBMS\\assign_1\\instructor.txt");
        BufferedReader br = new BufferedReader(new FileReader(f1));
        String st;

        while ((st = br.readLine()) != null) {
            String data[] = st.split(",");

            
            if (data.length > 1) {
                System.out.println(data[1]);
            } else {
                System.out.println("Not enough elements in the array");
            }
        }

        br.close(); 
    }
}
