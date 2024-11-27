import java.io.IOException;
import java.util.ArrayList;

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
        String searchPrompt = TextUI.messagePrompt("Enter the title or genre you want to search for:");

        // Search for prompt in movies
        ArrayList<String> matchedMovies = new ArrayList<>();
        for (String movie : mediaList.getMovieList()) {
            if (movie.toLowerCase().contains(searchPrompt.toLowerCase())) {
                matchedMovies.add(movie);
            }
        }

        // Search for prompt in series
        ArrayList<String> matchedSeries = new ArrayList<>();
        for (String series : mediaList.getSeriesList()) {
            if (series.toLowerCase().contains(searchPrompt.toLowerCase())) {
                matchedSeries.add(series);
            }
        }


        ArrayList<String> allResults = new ArrayList<>();
        allResults.addAll(matchedMovies);
        allResults.addAll(matchedSeries);

        if (allResults.isEmpty()) {
            TextUI.messagePrint("No results found for \"" + searchPrompt + "\".");
        } else {

            TextUI.messagePrint("Search results for \"" + searchPrompt + "\":");
            for (int i = 0; i < allResults.size(); i++) {
                TextUI.messagePrint((i + 1) + ". " + allResults.get(i));
            }

            String choice = TextUI.messagePrompt("Enter the number of the media you want to select, or '0' to go back.");

            try {
                int selectedMediaIndex = Integer.parseInt(choice);
                if (selectedMediaIndex == 0) {
                    return;
                } else if (selectedMediaIndex > 0 && selectedMediaIndex <= allResults.size()) {
                    String selectedMedia = allResults.get(selectedMediaIndex - 1);
                    String selectedMediaInfo = "";


                    if (matchedMovies.contains(selectedMedia)) {
                        int movieIndex = matchedMovies.indexOf(selectedMedia);
                        selectedMediaInfo = mediaList.getMovieInfoList().get(movieIndex);
                    } else if (matchedSeries.contains(selectedMedia)) {
                        int seriesIndex = matchedSeries.indexOf(selectedMedia);
                        selectedMediaInfo = mediaList.getSeriesInfoList().get(seriesIndex);
                    }

                    mediaList.handleMediaSelection(selectedMedia, selectedMediaInfo, "Movies");
                } else {
                    TextUI.messagePrint("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                TextUI.messagePrint("Invalid input.");
            }
        }
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
                                    searchBar();
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
