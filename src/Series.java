public class Series extends Media{
    private String seasons;
    private String episodes;
    public Series(String title, String genre, double rating, String releaseYear, String seasons, String episodes) {
        super(title, genre, rating, releaseYear);
        this.seasons = seasons;
        this.episodes = episodes;
    }
    // getters and setters
    public String getSeasons(){
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
