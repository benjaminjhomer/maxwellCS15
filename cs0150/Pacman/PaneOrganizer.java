package Pacman;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

/*
 * This class organizes the different Panes within the window. It has access to five
 * methods: getRoot(), createBottomPane(), createGamePane(), setScoreDis(), and setLives().
 * It also has a private class QuitHandler(). It is the top level object contained within 
 * App. 
 */

public class PaneOrganizer {

	private BorderPane _root;
	private Label _scoreDis;
	private Label _lives;
	private GridPane _gamePane;

	public PaneOrganizer() {
		_root = new BorderPane();
		this.createBottomPane();
		this.createGamePane();
	}

	// This method has no parameters and returns a BorderPane, _root.

	public BorderPane getRoot() {
		return _root;
	}

	/*
	 * This method has no parameters and returns nothing. It sets up the bottom pane
	 * with a quit button and labels which show the number of lives and the score.
	 * It sets this pane to the bottom of the _root.
	 */

	public void createBottomPane() {
		_lives = new Label("Lives: " + 3);

		_scoreDis = new Label("Score: " + 0);

		Button qt = new Button("Quit");
		qt.setOnAction(new QuitHandler());
		qt.setFocusTraversable(false);

		HBox bPane = new HBox(30);
		bPane.setAlignment(Pos.CENTER);
		bPane.getChildren().addAll(_lives, _scoreDis, qt);
		_root.setBottom(bPane);
	}

	/*
	 * This method has no parameters and returns nothing. It sets a 23X23 GridPane
	 * to the center of the _root. It creates a GameLogic object as well.
	 */

	public void createGamePane() {
		_gamePane = new GridPane();
		_gamePane.setStyle("-fx-background-color: blue;");
		_gamePane.setFocusTraversable(true);
		_gamePane.setAlignment(Pos.CENTER);
		_gamePane.setGridLinesVisible(true);
		_root.setCenter(_gamePane);
		for (int i = 0; i < Constants.BOARD_W; i++) {
			ColumnConstraints column = new ColumnConstraints(Constants.TILE_SIDE_L);
			_gamePane.getColumnConstraints().add(column);
		}
		for (int i = 0; i < Constants.BOARD_H; i++) {
			RowConstraints row = new RowConstraints(Constants.TILE_SIDE_L);
			_gamePane.getRowConstraints().add(row);
		}
		GameLogic game = new GameLogic(_gamePane, this);

	}

	/*
	 * This methods has an integer as a parameter and returns nothing. It sets the
	 * text of _scoreDis to the integer that was passed in.
	 */

	public void setScoreDis(int i) {
		_scoreDis.setText("Score: " + i);
	}

	/*
	 * This methods has an integer as a parameter and returns nothing. It sets the
	 * text of _lives to the integer that was passed in.
	 */

	public void setLives(int i) {
		_lives.setText("Lives: " + i);
	}

	/*
	 * This private class handles what should happen when the quit button is
	 * pressed. It exits the Platform.
	 */

	private class QuitHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Platform.exit();
		}

	}

}
