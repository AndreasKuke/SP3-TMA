import javax.xml.transform.Source;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        StreamingService streamingService = new StreamingService("data/film.csv", "data/serier.csv");
        streamingService.displayMain();

    }
}