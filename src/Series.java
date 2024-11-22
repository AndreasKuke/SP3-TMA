public class Series extends Media{
    private int seasons;
    private int episodes;
    public Series(String title, String genre, float rating, int releaseYear, int seasons, int episodes) {
        super(title, genre, rating, releaseYear);
        this.seasons = seasons;
        this.episodes = episodes;
    }
    // getters and setters
    public int getSeasons(){
        return seasons;
    }
    public void setSeasons(){
        this.seasons = seasons;
    }
    @Override
    public String getMediaInfo(){
        return super.getMediaInfo() + "\n" + String.format("Seasons: %d\nEpisodes: %d", seasons, episodes);
    }
}
