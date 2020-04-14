package DoodleJump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where your DoodleJump game will start. The main method
 * of this application calls launch, a JavaFX method which eventually calls the
 * start method below. You will need to fill in the start method to start your
 * game!
 *
 * This class displays all the graphical elements and contains the class
 * PaneOrganizer.
 */
public class App extends Application {

	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

		stage.setScene(scene);
		stage.setTitle("DoodleJump");
		stage.show();
	}

	/*
	 * Here is the mainline! No need to change this.
	 */
	public static void main(String[] argv) {
		// launch is a static method inherited from Application.
		launch(argv);
	}
}
