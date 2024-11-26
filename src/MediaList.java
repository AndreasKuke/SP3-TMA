import org.w3c.dom.Text;

import java.io.File;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

public class MediaList {
    private ArrayList<String> movieList;
    private ArrayList<String> seriesList;
    private ArrayList<String> watchList;
    private ArrayList<String> finishedList;
    private ArrayList<String> movieInfoList;
    private ArrayList<String> seriesInfoList;


    public MediaList(String filePathMovies, String filePathSeries) {
        movieList = new ArrayList<>();
        seriesList = new ArrayList<>();
        watchList = new ArrayList<>();
        finishedList = new ArrayList<>();
        loadMovies(filePathMovies);
        loadSeries(filePathSeries);

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
                    TextUI.messagePrint(title + " has been added to your watchlist");
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
                TextUI.messagePrint(title + "Has been removed from your watchList" + title);
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


    public void displayWatchList() {
        TextUI.messagePrint("Your watchList:");
        for (int i = 0; i<watchList.size();i++) {
            TextUI.messagePrint((i + 1) + ": " + watchList.get(i));
        }
    }


    public void displayFinishedList() {
        TextUI.messagePrint("Your finished list:");
        for (String media : finishedList) {
            TextUI.messagePrint(media);
        }
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
            String choice = TextUI.messagePrompt("Enter number of desired media or click '0' to go back.");

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

        private void handleMediaSelection(String selectedMedia, String selectedMediaInfo,String type){
            boolean inMediaMenu = true;
            while (inMediaMenu){
                System.out.println(selectedMediaInfo);
                TextUI.messagePrint("1. Start watching \"" + selectedMedia + "\".");
                TextUI.messagePrint("2. Add \"" + selectedMedia + "\"" + " to your watchlist.");
                TextUI.messagePrint("3. Go back to the " + type + " list.");
                TextUI.messagePrint("4. Go back to the main menu.");

                String choice = TextUI.messagePrompt("Enter your choice:");

                switch (choice){
                    case "1":
                        TextUI.messagePrint("Starting \"" + selectedMedia + "\"...");
                        addToFinishedList(selectedMedia);
                        System.exit(0); // filler code
                        break;
                    case "2":
                        addToWatchList(selectedMedia);
                        TextUI.messagePrint("Added \"" + selectedMedia + "\"" + " to your watchlist.");
                        break;
                    case "3":
                        displayMediaList(type);
                        break;
                    case "4":
                        inMediaMenu = false;
                        break;
                    default:
                        TextUI.messagePrint("Invalid choice. Please try again.");
                }
            }
        }
}
