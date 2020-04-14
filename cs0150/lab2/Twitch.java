package lab2;

public class Twitch implements VideoProvider {
    public Twitch() {
      this.getName();
      this.getTopVideos();
    }

    public String getName(){
      return "Twitch TV";
    }
    public String getTopVideos(){
      return "ChronoTrigger.mp4,SuperMarioBros.mp4,PokemonRed.mp4";
    }
}
