package Tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * App is called by to start the application and handles launching an dsiplaying the panes. It contains a PaneOrganizer. 
 */

public class App extends Application {

	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.BOARDW * (Constants.BLOCK_SIDE_L + 1),
				Constants.BOARDH * (Constants.BLOCK_SIDE_L + 2) + 100);
		// Create top-level object, set up the scene, and show the stage here.
		stage.setScene(scene);
		stage.setTitle("Tetris");
		stage.show();
	}

	/*
	 * Here is the mainline! No need to change this.
	 */
	public static void main(String[] argv) {
		// launch is a method inherited from Application
		launch(argv);
	}
}
