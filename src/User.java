import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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



    public void createUser(String name, String password) throws IOException{
        if (userExists(name)){
            System.out.println("A user with this name already exists. Be more creative.");
        }else{
            this.name = name;
            this.password = password;
            saveToFile();
            System.out.println("User has been created.");
        }


    }


    public boolean loginUser(String name, String password) throws IOException{
        if(userExists(name)){
            try(Scanner scanner = new Scanner(new File(FILE_NAME))){
                while(scanner.hasNextLine()){
                    String[] userDetails = scanner.nextLine().split(",");
                    if(userDetails[0].equals(name) && userDetails[1].equals(password)){
                        System.out.println("You are now logged in.");
                        return true;
                    }
                }
            }
            System.out.println("Login failed, wrong password.");
        }else{
            System.out.println("This user doesn't exist. Create one.");
        }
        return false;
    }

    //Saves to "data/users.csv" whatever the persons login information is, split up by a ","
    private void saveToFile() throws IOException{
        try (FileWriter writer = new FileWriter(FILE_NAME, true)){
            writer.write(this.name + "," + this.password+"\n");
        }
    }

    //Checks if a user exists by going through the file "users.csv".
    private boolean userExists(String name) throws IOException{
        File file = new File(FILE_NAME);
        if (!file.exists()){
            return false;
        }
        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                String[] userDetails = scanner.nextLine().split(",");
                if(userDetails[0].equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
}
