import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {







    private String name;
    private String password;
    private String FILE_NAME = "data/users.csv";


    public User(String name, String password){
        this.name = name;
        this.password = password;
    }


    //Default for if nothing exists, should prompt to create.
    public User(){
        this.name = null;
        this.password = null;
    }



    public void createUser() throws IOException{
        String name = TextUI.messagePrompt("Enter your desired username.");
        String password = TextUI.messagePrompt("Enter your desired password.");

        if (userExists(name)){
            TextUI.messagePrint("A user with this name already exists. Be more creative.");
        }else{
            this.name = name;
            this.password = password;
            FileIO.writeUserInfo(FILE_NAME,name + "," + password);
            TextUI.messagePrint("User has been created");
        }


    }


    public boolean loginUser() throws IOException {
        String name = TextUI.messagePrompt("Enter your username");
        String password = TextUI.messagePrompt("Enter your password");
        if (userExists(name)) {
            ArrayList<String> users = FileIO.readUserInfo(FILE_NAME);
            for (String line : users) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(name) && userDetails[1].equals(password)) {
                    TextUI.messagePrint("You are now logged in.");
                    return true;
                }
            }
            TextUI.messagePrint("Login failed. Wrong password.");
        }else{
            TextUI.messagePrint("User doesn't exist, create one.");
        }
        return false;
    }


    //Checks if a user exists by going through the file "users.csv".
    private boolean userExists(String name) throws IOException{
        ArrayList<String> users = FileIO.readUserInfo(FILE_NAME);
        for(String line : users){
            String[] userDetails = line.split(",");
            if(userDetails[0].equals(name)){
                return true;
            }
        }
        return false;
    }
}
