public class Media {
    private int releaseYear;
    private float rating;
    private String title;
    private String genre;

    public Media(String title, String genre, float rating, int releaseYear){
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.title = title;
        this.genre  = genre;

    }

    // getters and setters
    public int getReleaseYear(){
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
    }
    public float getRating(){
        return rating;
    }
    public void setRating(float rating){
        this.rating = rating;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    // get display info
    public String getMediaInfo(){
        return String.format("Title: %s\nGenre: %s\nRating: %.1f\nRelease year: %d",
                title, genre, rating, releaseYear);
    }

}
