package lab7;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import cs015.labs.PseudocodeSupport.PaneOrganizer;
import cs015.labs.PseudocodeSupport.Constants;

/**
 * This class is the main class! DO NOT EDIT.
**/

public class App extends Application {

 @Override
	public void start(Stage stage) {
		// Create top-level object, set up the scene, and show the stage here.
		PaneOrganizer organizer = PaneOrganizer.getInstance();
        organizer.setDrawer(new MyTurtleDrawer());
		Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Turtle Drawer");
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
