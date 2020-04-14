package Cartoon;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/*
 * This class organizes all the panes, instantiates and maintains a quit button 
 * and creates the cartoon.
 */

public class PaneOrganizer {
	
	private BorderPane _root;
	
	public PaneOrganizer() {
		_root = new BorderPane();
		Cartoon cartoon = new Cartoon(_root);
		cartoon.createSpaceShipPane();
		cartoon.createTopPane();
		cartoon.createEnemyPane();
		Button quit = new Button("QUIT"); //creates quit button
		quit.setTranslateX(CONSTANTS.QUIT_OFFSET);
		cartoon.getTopPane().getChildren().add(quit);
		quit.setOnAction(new QuitHandler());
		quit.setFocusTraversable(true);
		quit.setAlignment(Pos.BASELINE_RIGHT);
		quit.requestFocus();
	}
	/*
	 * This method takes in no parameter, and returns the _root pane.
	 */
	public Pane getRoot() {
		return _root;
	}
	
	/*
	 * This class listens for the quit button to be clicked. If it is it exits the App.
	 */
	
	private class QuitHandler implements EventHandler<ActionEvent> {
		public QuitHandler() {
			
		}
		@Override
		public void handle(ActionEvent e) {
			Platform.exit();
		}
	}
}
