import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.util.ArrayList;

public class MediaList {
    private ArrayList<String> movieList;
    private ArrayList<String> seriesList;
    private ArrayList<String> watchList;
    private ArrayList<String> finishedList;
    private ArrayList<String> movieInfoList;
    private ArrayList<String> seriesInfoList;
    private String watchListPath;
    private String finishedListPath;


    public MediaList(String filePathMovies, String filePathSeries, User user) {
        movieList = new ArrayList<>();
        seriesList = new ArrayList<>();
        watchList = new ArrayList<>();
        finishedList = new ArrayList<>();
        loadMovies(filePathMovies);
        loadSeries(filePathSeries);

        this.watchListPath = user.getWatchListPath();
        this.finishedListPath = user.getFinishedMediaPath();

        loadWatchList();
        loadFinishedList();

        movieInfoList = FileIO.readUserInfo(filePathMovies);
        seriesInfoList = FileIO.readUserInfo(filePathSeries);
    }

    private void loadMovies(String filePath) {
        ArrayList<String> movie = FileIO.readUserInfo(filePath);
        for (String s : movie){
            String movieName = s.split(";")[0].trim();
            String movieYear = s.split(";")[1].trim();
            String movieGenre = s.split(";")[2].trim();
            String movieRating = s.split(";")[3].trim();
            movieList.add(movieName);
        }
    }


    private void loadSeries(String filePath) {
        ArrayList<String> series = FileIO.readUserInfo(filePath);
        for(String s : series){
            String seriesName = s.split(";")[0].trim();
            seriesList.add(seriesName);
        }
    }

    public void displayWatchList() {
        TextUI.messagePrint("Your watchlist:");
        for (int i = 0; i < watchList.size(); i++){
            TextUI.messagePrint((i + 1) + ". " + watchList.get(i));
        }
        TextUI.messagePrint("0. Go back to main menu.");
        String choice = TextUI.messagePrompt("Enter number of media or 0 to go back:");

        try{
            int selectedMediaIndex = Integer.parseInt(choice);
            if (selectedMediaIndex == 0) {
                return; // Go back to the main menu
            }

            if (selectedMediaIndex > 0 && selectedMediaIndex <= watchList.size()) {
                String selectedMedia = watchList.get(selectedMediaIndex - 1); // Get selected media

                // Play media directly from the watchlist
                handleMediaSelection(selectedMedia, "Selected from Watchlist", "Watchlist");
            } else {
                TextUI.messagePrint("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            TextUI.messagePrint("Invalid input. Please enter a number.");
        }
    }

    public void displayFinishedList() {
        TextUI.messagePrint("Your finished list:");
        for (int i = 0; i < finishedList.size(); i++) {
            TextUI.messagePrint((i + 1) + ": " + finishedList.get(i)); // Show all media in finished list
        }
        TextUI.messagePrint("0. Go back to main menu.");

        String choice = TextUI.messagePrompt("Enter number of media you want to select or 0 to go back.");

        try {
            int selectedMediaIndex = Integer.parseInt(choice);
            if (selectedMediaIndex == 0) {
                return; // Go back to the main menu
            }

            if (selectedMediaIndex > 0 && selectedMediaIndex <= finishedList.size()) {
                String selectedMedia = finishedList.get(selectedMediaIndex - 1); // Get selected media

                // Play media directly from the finished list
                handleMediaSelection(selectedMedia, "Selected from Finished List", "Finished List");
            } else {
                TextUI.messagePrint("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            TextUI.messagePrint("Invalid input. Please enter a number.");
        }
    }


    public void addToWatchList(String title) {
        boolean mediaFound = false;
        for (String movie : movieList) {
            if (movie.contains(title) && !watchList.contains(movie)) {
                watchList.add(movie);
                TextUI.messagePrint(title + " has been added to your watchlist");
                mediaFound = true;
                break;
            }
        }
        if(!mediaFound){
            for (String series : seriesList){
                if(series.contains(title) && !watchList.contains(series)){
                    watchList.add(series);
                    mediaFound = true;
                    break;
                }
            }
        }
        if(!mediaFound){
            TextUI.messagePrint("Movie not found");
        }
    }

    public void removeFromWatchList(String title) {
        for (String media : watchList) {
            if (media.contains(title)) {
                watchList.remove(media);
                TextUI.messagePrint(title + "Has been removed from your watchList");
            }
        }
        TextUI.messagePrint("Title not found in your watchList");
    }


    public void addToFinishedList(String title) {
        boolean mediaFound = false;
        for (String movie : movieList) {
            if (movie.contains(title) && !finishedList.contains(movie)) {
                finishedList.add(movie);
                mediaFound = true;
                break;
            }
        }
    }

    private void loadWatchList() {
        watchList = FileIO.readUserInfo(watchListPath);
        watchList.removeIf(media -> media == null || media.trim().isEmpty());
    }

    private void loadFinishedList() {
        finishedList = FileIO.readUserInfo(finishedListPath);
        finishedList.removeIf(media -> media == null || media.trim().isEmpty());
    }

    public void saveWatchList() {
        watchList.removeIf(String::isEmpty);
        FileIO.writeUserInfo(watchListPath, String.join("\n", watchList));
    }

    public void saveFinishedList() {
        finishedList.removeIf(String::isEmpty);
        FileIO.writeUserInfo(finishedListPath, String.join("\n", finishedList));
    }

    public void saveLists() {
        saveWatchList();
        saveFinishedList();
    }

    public ArrayList<String> getWatchList(){
        return watchList;
    }
    public ArrayList<String> getFinishedList(){
        return finishedList;
    }


    public void displayMediaList(String type) {
        ArrayList<String> mediaList = new ArrayList<>();
        ArrayList<String> mediaInfoList = new ArrayList<>();

        if (type.equals("Movies")) {
            mediaList = movieList;
            mediaInfoList = movieInfoList;
        } else if (type.equals("Series")) {
            mediaList = seriesList;
            mediaInfoList = seriesInfoList;
        } else {
            TextUI.messagePrint("Invalid media type");
            return;
        }

        boolean inSubMenu = true;
        while (inSubMenu) {
            TextUI.messagePrint("\n" + type + " List:");
            for (int i = 0; i < mediaList.size(); i++) {
                TextUI.messagePrint((i + 1) + ". " + mediaList.get(i)); //Laver liste af media fra 1 til n-1
            }
            String choice = TextUI.messagePrompt("Enter number of desired media or click '0' to go back to main menu.");

            try {
                int intChoice = Integer.parseInt(choice);
                if (intChoice == 0) {
                    inSubMenu = false;
                }
                if (intChoice > 0 && intChoice <= mediaList.size()) {
                    String selectedMedia = mediaList.get(intChoice - 1);
                    String selectedMediaInfo = mediaInfoList.get(intChoice - 1);
                    handleMediaSelection(selectedMedia, selectedMediaInfo,type);
                } else {
                    TextUI.messagePrint("Invalid choice");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public ArrayList<String> getMovieList() {
        return movieList;
    }

    public ArrayList<String> getSeriesList() {
        return seriesList;
    }

    public void handleMediaSelection(String selectedMedia, String selectedMediaInfo,String type){
        boolean inMediaMenu = true;
        while (inMediaMenu){
            System.out.println(selectedMediaInfo);
            TextUI.messagePrint("1. Start watching \"" + selectedMedia + "\".");
            TextUI.messagePrint("2. Add \"" + selectedMedia + "\"" + " to your watchlist.");
            TextUI.messagePrint("3. Go back");

            String choice = TextUI.messagePrompt("Enter your choice:");

            switch (choice){
                case "1":
                    TextUI.messagePrint("Starting \"" + selectedMedia + "\"...");
                    addToFinishedList(selectedMedia);
                    playMedia(selectedMedia, type);
                    break;
                case "2":
                    addToWatchList(selectedMedia);
                    TextUI.messagePrint("Added \"" + selectedMedia + "\"" + " to your watchlist.");
                    break;
                case "3":
                    inMediaMenu = false;
                    break;
                default:
                    TextUI.messagePrint("Invalid choice. Please try again.");
            }
        }

    }
    private void playMedia(String selectedMedia, String type) {
        boolean watching = true;
        boolean isPaused = false;
        TextUI.messagePrint("Now playing \"" + selectedMedia + "\"...");

        while (watching) {
            TextUI.messagePrint("""
                    1. Pause/resume.
                    2. Quit watching.
                    """);
            String watchChoice = TextUI.messagePrompt("Enter your choice:");
            switch (watchChoice) {
                case "1":
                    if (!isPaused) {
                        TextUI.messagePrint("Paused \"" + selectedMedia + "\".");
                        isPaused = true;
                        break;
                    } else {
                        TextUI.messagePrint("Resumed \"" + selectedMedia + "\".");
                        isPaused = false;
                        break;
                    }
                case "2":
                    TextUI.messagePrint("Stopped watching \"" + selectedMedia + "\".");
                    watching = false;
                    break;
                default:
                    TextUI.messagePrint("Invalid choice. Please select 1 or 2.");
                    break;
            }
        }
    }


    public ArrayList<String> getSeriesInfoList() {
        return seriesInfoList;
    }

    public ArrayList<String> getMovieInfoList() {
        return movieInfoList;
    }
}