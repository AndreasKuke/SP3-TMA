public class Movie extends Media{
    private int durationInMinutes;

    public Movie(String title, String genre, float rating, int releaseYear, int durationInMinutes) {
        super(title, genre, rating, releaseYear);
        this.durationInMinutes = durationInMinutes;
    }

    // getter and setter
    public int getDurationInMinutes(){
        return durationInMinutes;
    }
    public void setDurationInMinutes(int durationInMinutes){
        this.durationInMinutes = durationInMinutes;
    }
    @Override
    public String getMediaInfo(){
        return super.getMediaInfo() + "\n" + String.format("Duration: %d minutes", durationInMinutes);
    }
}
