package AndyBot;

import cs015.prj.AndyBotSupport.AndyBot;
import cs015.prj.AndyBotSupport.AndyBotFactory;
import cs015.prj.AndyBotSupport.AndyBotHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Welcome to your AndyBot App class.
 * Please do not modify this file at all.
 *
 */

public class App extends Application{

    @Override
    public void start(Stage stage) {
        AndyBotFactory factory = new AndyBotFactory();
        AndyBot andybot = factory.makeAndyBot();
        new AndyBotHelper(stage, new MazeSolver(andybot));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
