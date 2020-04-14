package Tetris;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/*
 * This class is responsible for organizing all the Panes on the screen
 * graphically. It has access to the following methods: getRoot, createMenuPane,
 * setScore, pauseLabel, addGameOver,createGamePane. It has two inner classes,
 * QuitHandler and Restart Handler.
 */

public class PaneOrganizer {

	private BorderPane _root;
	private HBox _menuPane;
	private Label _scoreDisplay;
	private GridPane _gamePane;
	private GameLogic _game;
	private Label _p;
	private Pane _topPane;

	public PaneOrganizer() {
		_root = new BorderPane();
		_root.setStyle(Constants.PANECOLOR);
		this.createGamePane();
		this.createMenuPane();

	}
	
	// Returns the root pane. Has no parameters.

	public BorderPane getRoot() {
		return _root;
	}

	/*
	 * This method creates the bottom pane which will contain the score, quit
	 * button, and restart button. It has no parameters and returns nothing.
	 */

	public void createMenuPane() {
		_scoreDisplay = new Label(Integer.toString(_game.getScore()));
		_scoreDisplay.setTextFill(Color.ORANGE);
		_scoreDisplay.setFont(new Font("Helvetica", 24));

		Label scoreLabel = new Label("Score");
		scoreLabel.setTextFill(Color.ORANGE);
		scoreLabel.setFont(new Font("Helvetica", 24));

		VBox labelPane = new VBox(2);
		labelPane.getChildren().addAll(scoreLabel, _scoreDisplay);
		labelPane.setAlignment(Pos.CENTER);

		Button qtBtn = new Button("Quit");
		qtBtn.setFocusTraversable(false);
		qtBtn.setOnAction(new QuitHandler());

		Button rstBtn = new Button("Restart");
		rstBtn.setFocusTraversable(false);
		rstBtn.setOnAction(new RestartHandler());

		VBox buttonPane = new VBox(5);
		buttonPane.getChildren().addAll(qtBtn, rstBtn);
		buttonPane.setAlignment(Pos.CENTER);

		_menuPane = new HBox(200);
		_menuPane.setStyle(Constants.PANECOLOR);
		_menuPane.setAlignment(Pos.BOTTOM_CENTER);
		_menuPane.getChildren().addAll(labelPane, buttonPane);
		_root.setBottom(_menuPane);
	}

	/*
	 * This method has no parameters and returns nothing. It updates the score label
	 * to reflect what the instance variable _score is when called.
	 */

	public void setScore() {
		_scoreDisplay.setText(Integer.toString(_game.getScore()));
	}

	/*
	 * This class quits the app when the button it is assigned to is clicked on. It
	 * implements the interface EventHandler.
	 */

	private class QuitHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Platform.exit();
		}

	}

	/*
	 * This class remakes the game pane the app when the button it is assigned to is
	 * clicked on. It implements the interface EventHandler.
	 */

	private class RestartHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			_root.getChildren().remove(_gamePane);
			PaneOrganizer.this.createGamePane();
		}

	}

	/*
	 * This method changes the pause label to pause when it is passed a boolean of
	 * true. It returns nothing.
	 */

	public void pauseLabel(boolean b) {
		if (b) {
			_p.setText("PAUSED");
		} else {
			_p.setText("TETRIS");
		}
	}

	/*
	 * Add a game over label to the pane. It takes in a label as a parameter and
	 * returns nothing.
	 */

	public void addGameOver(Label l) {
		_root.setCenter(l);
	}

	/*
	 * This method creates and adds the pane that holds the game to the grid pane.
	 * It takes in no parameters and returns nothing. It is called every time the
	 * game is (re)started.
	 */

	public void createGamePane() {
		_gamePane = new GridPane();
		_gamePane.setAlignment(Pos.CENTER);
		_gamePane.setGridLinesVisible(true);
		_gamePane.setStyle("-fx-background-color: grey");
		_root.setCenter(_gamePane);
		_gamePane.setHgap(1);
		_gamePane.setVgap(1);
		for (int i = 0; i < Constants.BOARDW; i++) {
			ColumnConstraints column = new ColumnConstraints(Constants.BLOCK_SIDE_L);
			_gamePane.getColumnConstraints().add(column);
		}
		for (int i = 0; i < Constants.BOARDH; i++) {
			RowConstraints row = new RowConstraints(Constants.BLOCK_SIDE_L);
			_gamePane.getRowConstraints().add(row);
		}
		_game = new GameLogic(_gamePane, this);
		_game.setupTimeline();

		_topPane = new Pane();
		_topPane.setMinHeight(50);
		_topPane.setStyle(Constants.PANECOLOR);

		_root.setTop(_topPane);
		_p = new Label("TETRIS");
		_p.setLayoutX(Constants.BOARDW * (Constants.BLOCK_SIDE_L + 1) / 2 - Constants.TOPLABELHALF);
		_p.setTextFill(Color.ORANGE);
		_p.setFont(new Font("Helvetica", 30));
		_topPane.getChildren().add(_p);

	}

}
