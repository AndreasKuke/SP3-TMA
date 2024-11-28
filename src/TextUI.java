import java.util.Scanner;

public class TextUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static String messagePrompt(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public static void messagePrint(String message){
        System.out.println(message);
    }



}
