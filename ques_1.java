import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
public class ques_1{
    public static void main(String[] args){
        // code to create a new file
        File myFile = new File("instructor.txt");
        try{
            myFile.createNewFile();
        } catch (IOException e){
            System.out.println("Unable to create this file");
            e.printStackTrace();
        }
        // code to write a file
        try{
        FileWriter fileWriter = new FileWriter("instructor.txt");
        fileWriter.write("This is our first file from this java course");
        fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}