import java.util.ArrayList;
import java.util.List;

public class MediaList {
    private ArrayList<String> movieList;
    private ArrayList<String> seriesList;
    private ArrayList<String> watchList;
    private ArrayList<String> finishedList;

    public MediaList(String filePathMovies, String filePathSeries) {
        movieList = new ArrayList<>();
        seriesList = new ArrayList<>();
        watchList = new ArrayList<>();
        finishedList = new ArrayList<>();
        loadMovies(filePathMovies);
        loadSeries(filePathSeries);
    }

    private void loadMovies(String filePath) {
        ArrayList<String> movie = FileIO.readUserInfo(filePath);
        for (String s : movie){
            String movieName = s.split(";")[0].trim();
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


    public void addToFinishedList() {

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

}
