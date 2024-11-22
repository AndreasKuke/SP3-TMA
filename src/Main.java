import javax.xml.transform.Source;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        User user = new User();

        while(true){
            TextUI.messagePrint("Welcome to Netflix & Chill");
            TextUI.messagePrint("1. Login to your account");
            TextUI.messagePrint("2. Create new account");

            String choice = TextUI.messagePrompt("Enter your choice:");

            switch (choice){
                case "1":
                    user.loginUser();
                    break;
                case "2":
                    user.createUser();
                    break;
                default:
                    TextUI.messagePrint("Invalid choice, read what it says.");
            }
        }
    }
}
