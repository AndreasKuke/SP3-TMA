import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    public static ArrayList<String> readUserInfo(String path){
        ArrayList<String> userData = new ArrayList<>();
        File file = new File(path);
        try{
            Scanner scan = new Scanner(file);

            while(scan.hasNextLine()){
                String line = scan.nextLine();
                userData.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return userData;
    }

    public static void writeUserInfo(String path, String data){
        try(FileWriter writer = new FileWriter(path,true)){
            writer.write(data+"\n");
        }catch (IOException e){
            System.out.println("Failed to write to file");
        }
    }
}
