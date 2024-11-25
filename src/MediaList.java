import java.lang.reflect.Array;
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

    public void displayMediaList(String type){
        ArrayList<String> mediaList = new ArrayList<>();

        if(type.equals("Movies")){
            mediaList = movieList;
        }else if(type.equals("Series")){
            mediaList = seriesList;
        }else{
            TextUI.messagePrint("Invalid media type");
            return;
        }



        TextUI.messagePrint("\n"+type+" List:");
        for (int i = 0; i < mediaList.size(); i++){
            TextUI.messagePrint((i+1) + ". " +mediaList.get(i)); //Laver liste af media fra 1 til n-1
        }
        String choice = TextUI.messagePrompt("Enter number of desired media or click '0' to go back.");

        try{
            int intChoice = Integer.parseInt(choice);
            if(intChoice == 0){
                return;
            }
            if (intChoice > 0 && intChoice <= mediaList.size()){
                TextUI.messagePrint("You have selected: " + mediaList.get(intChoice));
            }else{
                TextUI.messagePrint("Invalid choice");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<String> getMovieList() {
        return movieList;
    }

    public ArrayList<String> getSeriesList() {
        return seriesList;
    }
}
