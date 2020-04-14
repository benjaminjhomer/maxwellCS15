package lab2;

import javafx.application.Application;
import javafx.stage.Stage;
import cs015.labs.VideoLabSupport.SupportMain;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is the entry point for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // DO NOT MODIFY THE FOLLOWING LINE.
        SupportMain main = new SupportMain(primaryStage);
        VideoPlayer myVideoPlayer = new VideoPlayer();
        main.addVideoPlayer(myVideoPlayer);
        // Add any other method calls you may need here.
    }
}
