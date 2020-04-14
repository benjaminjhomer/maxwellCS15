package lab2;

public class Nature implements VideoProvider {
    public Nature() {
      this.getName();
      this.getTopVideos();
    }

    public String getName(){
      return "Nature Channel";
    }
    public String getTopVideos(){
      return "Elephant.mp4,Koala.mp4,Turtle.mp4";
    }
}
