package Pacman;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class where your Pacman game will start. The main method of
 * this application calls the App constructor. You will need to fill in the
 * constructor to instantiate your game.
 *
 * Class comments here... The App class contains an instance of PaneOrganizer
 * and Scene. It is a child of the class Application and will show and start the
 * window to show the game.
 */

public class App extends Application {

	@Override
	public void start(Stage stage) {
		// Create top-level object, set up the scene, and show the stage here.
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.BOARD_W * (Constants.TILE_SIDE_L),
				Constants.BOARD_H * (Constants.TILE_SIDE_L) + 30 );
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
