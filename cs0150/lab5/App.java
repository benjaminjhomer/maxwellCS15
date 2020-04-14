package lab5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    /**
     * The main class - this is the entry point to the entire program.
     * @param args - just the command line arguments
     */
    public static void main(String[] args){
            launch(args);
    }

    /**
     * Javafx magic calls this method. The primaryStage is the frame in which
     * everything else is included.
     */
    @Override
    public void start(Stage primaryStage) {
    	//outer stage setup
    	primaryStage.setTitle("JavaFX Lab!");
    	primaryStage.setScene(new Scene(new TabOrganizer().toNode()));
    	primaryStage.show();
    }
}
