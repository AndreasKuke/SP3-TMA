import javax.xml.transform.Source;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("Welcome to 'Netflix & Chill', what is your next move?");
        System.out.println("1. Log into an account");
        System.out.println("2. Create an user");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 2){
            System.out.println("Enter preferred Username");
            String name = scanner.nextLine();

            System.out.println("Enter preferred Password");
            String password = scanner.nextLine();

            user.createUser(name, password);
        }else if (choice == 1){
            System.out.println("Enter Username");
            String name = scanner.nextLine();

            System.out.println("Enter Password");
            String password = scanner.nextLine();
        }else{
            System.out.println("Invalid choice, try again");
        }
        scanner.close();
    }
}
