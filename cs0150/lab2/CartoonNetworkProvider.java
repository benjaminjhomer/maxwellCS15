package lab2;

public class CartoonNetworkProvider implements VideoProvider {
    public CartoonNetworkProvider() {
      this.getName();
      this.getTopVideos();
    }

    public String showAMovie() {
       return "PopeyeForPres.mp4";
    }
    public String getName(){
      return "Cartoon Network Channel";
    }
    public String getTopVideos(){
      return "PopeyeForPres.mp4,PopeyeCookingWithGags.mp4,PopeyeMeetsSinbad.mp4";
    }
}
