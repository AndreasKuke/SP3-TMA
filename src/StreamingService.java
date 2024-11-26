import java.io.IOException;

public class StreamingService {
    private User currentUser;
    private MediaList mediaList;
    private String filePathMovies;
    private String filePathSeries;
    private String userDataPath;


    public StreamingService(String filePathMovies, String filePathSeries) {
        this.filePathMovies = filePathMovies;
        this.filePathSeries = filePathSeries;
        currentUser = new User();

    }

    public void searchBar() {

    }

    public void displayMain() throws IOException {
        while (true) {
            TextUI.messagePrint("Welcome to Netflix & Chill");
            TextUI.messagePrint("1. Login to your account");
            TextUI.messagePrint("2. Create new account");

            String choice = TextUI.messagePrompt("Enter your choice:");

            switch (choice) {
                case "1":
                    if (currentUser.loginUser()) {
                        mediaList = new MediaList(filePathMovies, filePathSeries, currentUser);
                        boolean ifLogged = true;
                        while (ifLogged) {
                            TextUI.messagePrint("\nMain menu:");
                            TextUI.messagePrint("1. Movies");
                            TextUI.messagePrint("2. Series");
                            TextUI.messagePrint("3. Search");
                            TextUI.messagePrint("4. Watchlist");
                            TextUI.messagePrint("5. Finished media");
                            TextUI.messagePrint("6. Logout");

                            String mainMenuChoice = TextUI.messagePrompt("Enter your choice:");

                            switch (mainMenuChoice) {
                                case "1":
                                    TextUI.messagePrint("Displaying movies.");
                                    mediaList.displayMediaList("Movies");
                                    break;
                                case "2":
                                    TextUI.messagePrint("Displaying series");
                                    mediaList.displayMediaList("Series");
                                    break;
                                case "3":
                                    TextUI.messagePrint("You can search for a movie or genre");
                                    break;
                                case "4":
                                    TextUI.messagePrint("Here is your watchlist");
                                    mediaList.displayWatchList();
                                    break;
                                case "5":
                                    TextUI.messagePrint("List over your finished media");
                                    mediaList.displayFinishedList();
                                    break;
                                case "6":
                                    TextUI.messagePrint("Logging out");
                                    mediaList.saveLists();
                                    ifLogged = false;
                                    break;

                                default:
                                    TextUI.messagePrint("Invalid choice.");
                            }
                        }
                    }

                        break;
                        case "2":
                            currentUser.createUser();
                            break;
                        default:
                            TextUI.messagePrint("Invalid choice, read what it says.");
            }
        }
    }
}
