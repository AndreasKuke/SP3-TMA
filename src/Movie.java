public class Movie extends Media{
    private String durationInMinutes;

    public Movie(String title, String genre, double rating, String releaseYear) {
        super(title, genre, rating, releaseYear);
         this.durationInMinutes = durationInMinutes;
    }

    // getter and setter
    public String getDurationInMinutes(){
        return durationInMinutes;
    }
    public void setDurationInMinutes(String durationInMinutes){
        this.durationInMinutes = durationInMinutes;
    }
    @Override
    public String getMediaInfo(){
        return super.getMediaInfo() + "\n" + String.format("Duration: %d minutes", durationInMinutes);
    }
}
