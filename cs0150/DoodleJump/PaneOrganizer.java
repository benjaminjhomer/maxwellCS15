package DoodleJump;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/*
 * This class sets up all graphical elements and organizes them within the _root. It has 
 * access to getRoot(), createBottomPane(),createGamePane(),getters for the instance 
 * variables needed in Game. 
 */

public class PaneOrganizer {

	private BorderPane _root;
	private Pane _jumpSpace;
	private Label _gameState;
	private VBox _bottomPane;
	ArrayList<Ledge> _ledge = new ArrayList<Ledge>();
	private Doodle _dood;
	private int _score;
	private Label _scoreLabel;
	private Button _rstBtn;
	private Game _doodleJump;

	public PaneOrganizer() {
		_root = new BorderPane();
		_doodleJump = new Game(this, _root);
		this.createBottomPane();
		this.createGamePane();
		_doodleJump.setInstVar();
		_doodleJump.setupTimeline();
	}

	public Pane getRoot() {
		return _root;
	}

	/*
	 * This method creates the bottom pane which contains instructions, a label that
	 * switched between Jump! and Game Over!, a score label, and a quit button. It
	 * doesn't have any parameters and doesn't return anything.
	 */

	public void createBottomPane() {

		Label instructions = new Label(
				"Press left and right arrows to move the doodle. " + "\n Don't let the doodle hit the bottom!");
		instructions.setTextAlignment(TextAlignment.CENTER);
		instructions.setAlignment(Pos.CENTER);
		instructions.setMinSize(Constants.WINDOW_WIDTH, 25);

		_gameState = new Label("Jump!");
		_gameState.setTextAlignment(TextAlignment.CENTER);
		_gameState.setAlignment(Pos.CENTER);
		_gameState.setMinSize(Constants.WINDOW_WIDTH, Constants.LABEL_HEIGHT);

		_score = 0;
		_scoreLabel = new Label("Score: " + _score);
		_scoreLabel.setTextAlignment(TextAlignment.CENTER);
		_scoreLabel.setAlignment(Pos.CENTER);
		_scoreLabel.setMinSize(Constants.WINDOW_WIDTH, Constants.LABEL_HEIGHT);

		Button quitBtn = new Button("Quit");
		quitBtn.setOnAction(new QuitHandler());
		quitBtn.setFocusTraversable(false);

		_bottomPane = new VBox(5);
		_bottomPane.setPrefSize(Constants.WINDOW_WIDTH, Constants.BOTTOM_HEIGHT);
		_bottomPane.setStyle("-fx-background-color: grey");
		_bottomPane.setAlignment(Pos.CENTER);
		_bottomPane.getChildren().addAll(instructions, _gameState, _scoreLabel, quitBtn);

		_root.setStyle("-fx-background-color: rgba(0,0,0,0)");
		_root.setBottom(_bottomPane);
	}

	/*
	 * This method creates the pane in which the game will be played in. It will
	 * have a Doodle and multiple ledges in it. It has no parameters and returns
	 * nothing.
	 */

	public void createGamePane() {
		_jumpSpace = new Pane();
		_root.getChildren().add(_jumpSpace);
		_jumpSpace.addEventHandler(KeyEvent.KEY_PRESSED, _doodleJump.getKeyHandler());
		_jumpSpace.setFocusTraversable(true);
		_jumpSpace.requestFocus();

		_dood = new Doodle(_jumpSpace);

		Ledge ledge = new Ledge(_jumpSpace);
		ledge.setPosition(Constants.FIRST_LEDGE_X, Constants.FIRST_LEDGE_Y);
		_ledge.add(ledge);
	}

	// This method is a getter for the instance variable _dood.

	public Doodle getDood() {
		return _dood;
	}

	// This method is a getter for the instance variable _jumpSpace.

	public Pane getJS() {
		return _jumpSpace;
	}

	// This method is a getter for the instance variable _ledge.

	public ArrayList<Ledge> getLedge() {
		return _ledge;
	}

	// This method is a getter for the instance variable _bottomPane.

	public VBox getBPane() {
		return _bottomPane;
	}

	// This method is a getter for the instance variable _gameState.

	public Label getGS() {
		return _gameState;
	}

	// This method is a getter for the instance variable _scoreLabel.

	public Label getSL() {
		return _scoreLabel;
	}

	// This method is a getter for the instance variable _score.

	public int getScore() {
		return _score;
	}

	// This method is a getter for the instance variable _rstBtn.

	public Button getRstBtn() {
		return _rstBtn;
	}

	/*
	 * This class handles all clicks on the quit button created by
	 * createBottomPane() by exiting the application. It's handle method listen for
	 * a click.
	 */

	private class QuitHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			Platform.exit();
		}

	}
}
